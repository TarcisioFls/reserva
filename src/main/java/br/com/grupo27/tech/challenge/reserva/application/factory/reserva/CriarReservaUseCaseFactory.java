package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.CriarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.CriarReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.BuscarClientePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservasPorClientIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservasPorRestauranteIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.CriarReservaAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.BuscarRestaurantePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@NoArgsConstructor
public class CriarReservaUseCaseFactory {

    public CriarReservaUserCase buildCriarReservaUseCase(CriarReservaPresenter criarReservaPresenter,
                                                         ReservaPresenter reservaPresenter,
                                                         ReservaRepository reservaRepository,
                                                         RestauranteRepository restauranteRepository,
                                                         RestaurantePresenter restaurantePresenter,
                                                         ClienteRepository clienteRepository,
                                                         ClientePresenter clientePresenter,
                                                         Clock clock) {

        return new CriarReservaUserCase(criarReservaPresenter,
                buildCriarReservaGateway(reservaPresenter, reservaRepository),
                buildBuscarRestaurantePorIdGateway(restauranteRepository, restaurantePresenter),
                buildBuscarReservaPorRestauranteIdGateway(reservaPresenter, reservaRepository),
                buildBuscarReservasPorClientIdGateway(reservaPresenter, reservaRepository),
                buildBuscarClientePorIdGateway(clienteRepository, clientePresenter),
                clock);
    }

    private BuscarClientePorIdAdapter buildBuscarClientePorIdGateway(ClienteRepository clienteRepository, ClientePresenter clientePresenter) {
        return new BuscarClientePorIdAdapter(clienteRepository, clientePresenter);
    }

    private BuscarReservasPorClientIdAdapter buildBuscarReservasPorClientIdGateway(ReservaPresenter reservaPresenter, ReservaRepository reservaRepository) {
        return new BuscarReservasPorClientIdAdapter(reservaRepository, reservaPresenter);
    }

    private BuscarReservasPorRestauranteIdAdapter buildBuscarReservaPorRestauranteIdGateway(ReservaPresenter reservaPresenter, ReservaRepository reservaRepository) {
        return new BuscarReservasPorRestauranteIdAdapter(reservaRepository, reservaPresenter);
    }

    private BuscarRestaurantePorIdAdapter buildBuscarRestaurantePorIdGateway(RestauranteRepository restauranteRepository, RestaurantePresenter restaurantePresenter) {
        return new BuscarRestaurantePorIdAdapter(restauranteRepository, restaurantePresenter);
    }

    private CriarReservaAdapter buildCriarReservaGateway(ReservaPresenter reservaPresenter, ReservaRepository reservaRepository) {
        return new CriarReservaAdapter(reservaRepository, reservaPresenter);
    }
}
