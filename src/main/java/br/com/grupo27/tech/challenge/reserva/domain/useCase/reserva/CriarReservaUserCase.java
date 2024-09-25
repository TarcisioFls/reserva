package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.CriarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.CriarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.CriarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.CriarReservaPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CAPACIDADE_DO_RESTAURANTE_ATINGIDA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CLIENTE_JA_POSSUI_RESERVA_NESTE_HORARIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CLIENTE_JA_POSSUI_RESERVA_NESTE_RESTAURANTE;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CLIENTE_NAO_ENCONTRADO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.DATA_HORA_PASSADO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_FORA_DO_HORARIO_PERMITIDO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;
import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

@Getter
@RequiredArgsConstructor
public class CriarReservaUserCase {

    private final CriarReservaPresenter criarReservaPresenter;
    private final CriarReservaGateway criarReservaGateway;
    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;
    private final BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;
    private final BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;
    private final BuscarClientePorIdGateway buscarClientePorIdGateway;
    private final Clock clock;

    public CriarReservaOutput criar(CriarReservaInput criarReservaInput) {

        var reserva = criarReservaPresenter.criarReservaInputEmReserva(criarReservaInput);

        if (LocalDateTime.now(clock).isAfter(reserva.getDataHora())) {
            throw new ExceptionAdvice(DATA_HORA_PASSADO);
        }

        buscarClientePorIdGateway.buscarClientePorId(reserva.getClienteId())
                .orElseThrow(() -> new ExceptionAdvice(CLIENTE_NAO_ENCONTRADO));

        var restaurante = buscarRestaurantePorIdGateway.buscarPorId(reserva.getRestauranteId())
                .orElseThrow(() -> new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO));

        var reservasDoRestaurante = buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEStatusReservadoEDataHora(
                reserva.getRestauranteId(), RESERVADO, reserva.getDataHora());

        var reservaAuxiliar = reserva;
        reservasDoRestaurante.ifPresent(reservas -> {
            var pessoasReservadas = reservas.stream().mapToInt(Reserva::getQuantidadePessoas).sum();
            if (pessoasReservadas + reservaAuxiliar.getQuantidadePessoas() > restaurante.getCapacidade()) {
                throw new ExceptionAdvice(CAPACIDADE_DO_RESTAURANTE_ATINGIDA);
            }
            reservas.stream().filter(r -> r.getClienteId().equals(reservaAuxiliar.getClienteId()))
                    .findAny().ifPresent(r -> {
                        throw new ExceptionAdvice(CLIENTE_JA_POSSUI_RESERVA_NESTE_RESTAURANTE);
                    });
        });

        var formatter = DateTimeFormatter.ofPattern("HH:mm");
        var horaAbertura = LocalTime.parse(restaurante.getHoraAbertura(), formatter);
        var horaFechamento = LocalTime.parse(restaurante.getHoraFechamento(), formatter);
        if (reserva.getDataHora().toLocalTime().isBefore(horaAbertura) || reserva.getDataHora().toLocalTime().isAfter(horaFechamento)) {
            throw new ExceptionAdvice(RESERVA_FORA_DO_HORARIO_PERMITIDO);
        }

        buscarReservasPorClientIdGateway.buscarPorClientIdEStatusReservadoEDataHora(reserva, RESERVADO).stream()
                .filter(reservaCliente -> reservaCliente.getDataHora().equals(reservaAuxiliar.getDataHora()))
                .findAny().ifPresent(reservaCliente -> {
                    throw new ExceptionAdvice(CLIENTE_JA_POSSUI_RESERVA_NESTE_HORARIO);
                });

        reserva = criarReservaGateway.criar(reserva);

        return criarReservaPresenter.reservaEmCriarReservaOutput(reserva);
    }
}
