package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.DeletaRestaurantePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.DeletaReservasPorRestauranteIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.BuscarRestaurantePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.DeletaRestaurantePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DeletaRestaurantePorIdUserCaseFactory {

    public DeletaRestaurantePorIdUserCase buildDeletaRestaurantePorIdUserCase(
            RestauranteRepository restauranteRepository,
            RestaurantePresenter restaurantePresenter,
            ReservaRepository reservaRepository) {
        return new DeletaRestaurantePorIdUserCase(
                buildDeletaRestauranteGateway(restauranteRepository),
                buildBuscarRestaurantePorIdGateway(restauranteRepository, restaurantePresenter),
                buildDeletaReservaPorRestauranteIdGateway(reservaRepository)
        );
    }

    private DeletaRestaurantePorIdAdapter buildDeletaRestauranteGateway(RestauranteRepository restauranteRepository) {
        return new DeletaRestaurantePorIdAdapter(restauranteRepository);
    }

    private BuscarRestaurantePorIdAdapter buildBuscarRestaurantePorIdGateway(RestauranteRepository restauranteRepository, RestaurantePresenter restaurantePresenter) {
        return new BuscarRestaurantePorIdAdapter(restauranteRepository, restaurantePresenter);
    }

    private DeletaReservasPorRestauranteIdAdapter buildDeletaReservaPorRestauranteIdGateway(ReservaRepository reservaRepository) {
        return new DeletaReservasPorRestauranteIdAdapter(reservaRepository);
    }
}
