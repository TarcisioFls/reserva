package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.AtualizarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarReservaPresenter;
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
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_NAO_ENCONTRADA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;

@Getter
@RequiredArgsConstructor
public class AtualizarReservaUserCase {

    private final AtualizarReservaPresenter atualizarReservaPresenter;
    private final Clock clock;
    private final BuscarClientePorIdGateway buscarClientePorIdGateway;
    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;
    private final BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;
    private final BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;
    private final AtualizarReservaGateway atualizarReservaGateway;

    public AtualizarReservaOutput atualizar(AtualizarReservaInput atualizarReservaInput) {

        var reserva = atualizarReservaGateway.buscarPorId(atualizarReservaInput.getId())
                .orElseThrow(() -> new ExceptionAdvice(RESERVA_NAO_ENCONTRADA));

        reserva = atualizarReservaPresenter.atualizarReservaInputEmReserva(reserva, atualizarReservaInput);

        if (LocalDateTime.now(clock).isAfter(reserva.getDataHora())) {
            throw new ExceptionAdvice(DATA_HORA_PASSADO);
        }

        buscarClientePorIdGateway.buscarClientePorId(reserva.getClienteId())
                .orElseThrow(() -> new ExceptionAdvice(CLIENTE_NAO_ENCONTRADO));

        var restaurante = buscarRestaurantePorIdGateway.buscarPorId(reserva.getRestauranteId())
                .orElseThrow(() -> new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO));

        var reservasDoRestaurante = buscarReservasPorRestauranteIdGateway.buscarPorRestauranteIdEDataHora(
                reserva.getRestauranteId(), reserva.getDataHora());

        var reservaAuxiliar = reserva;
        reservasDoRestaurante.ifPresent(reservas -> {
            var pessoasReservadas = reservas.stream().filter(r -> !r.getId().equals(reservaAuxiliar.getId()))
                    .mapToInt(Reserva::getQuantidadePessoas).sum();
            if (pessoasReservadas + reservaAuxiliar.getQuantidadePessoas() > restaurante.getCapacidade()) {
                throw new ExceptionAdvice(CAPACIDADE_DO_RESTAURANTE_ATINGIDA);
            }

            reservas.stream().filter(r -> r.getClienteId().equals(reservaAuxiliar.getClienteId()))
                    .filter(r -> !r.getId().equals(reservaAuxiliar.getId()))
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

        buscarReservasPorClientIdGateway.buscarPorClientIdEDataHora(reserva).stream()
                .filter(r -> !r.getId().equals(reservaAuxiliar.getId()))
                .findAny().ifPresent(r -> {
            throw new ExceptionAdvice(CLIENTE_JA_POSSUI_RESERVA_NESTE_HORARIO);
        });

        reserva = atualizarReservaGateway.atualizar(reserva);

        return atualizarReservaPresenter.reservaEmAtualizarReservaOutput(reserva);
    }
}
