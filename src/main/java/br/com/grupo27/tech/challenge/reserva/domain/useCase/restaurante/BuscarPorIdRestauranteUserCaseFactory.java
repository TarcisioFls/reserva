package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.BuscarRestaurantePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarPorIdRestauranteUserCaseFactory {

    public BuscarRestaurantePorIdUserCase buildBuscarRestaurantePorIdUserCase(BuscarRestaurantePorIdPresenter buscarRestaurantePorIdPresenter,
                                                                              RestaurantePresenter restaurantePresenter,
                                                                              RestauranteRepository restauranteRepository) {
        return new BuscarRestaurantePorIdUserCase(
                buildBuscarRestaurantePorIdGateway(restaurantePresenter, restauranteRepository),
                buscarRestaurantePorIdPresenter
        );
    }

    private BuscarRestaurantePorIdAdapter buildBuscarRestaurantePorIdGateway(RestaurantePresenter restaurantePresenter, RestauranteRepository restauranteRepository) {
        return new BuscarRestaurantePorIdAdapter(restauranteRepository, restaurantePresenter);
    }
}
