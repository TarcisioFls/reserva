package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.AtualizarReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.AtualizarReservaAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class AtualizarReservaUserCaseFactory {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    public AtualizarReservaUserCase buildAtualizarReservaUserCase(AtualizarReservaPresenter atualizarReservaPresenter,
                                                                  Clock clock,
                                                                  BuscarClientePorIdGateway buscarClientePorIdGateway,
                                                                  BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway,
                                                                  BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway,
                                                                  BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway) {

        return new AtualizarReservaUserCase(
                atualizarReservaPresenter,
                clock,
                buscarClientePorIdGateway,
                buscarRestaurantePorIdGateway,
                buscarReservasPorRestauranteIdGateway,
                buscarReservasPorClientIdGateway,
                buildAtualizarReservaGateway(reservaRepository, reservaPresenter));
    }

    private AtualizarReservaAdapter buildAtualizarReservaGateway(ReservaRepository reservaRepository, ReservaPresenter reservaPresenter) {

        return new AtualizarReservaAdapter(reservaRepository, reservaPresenter);
    }

}
