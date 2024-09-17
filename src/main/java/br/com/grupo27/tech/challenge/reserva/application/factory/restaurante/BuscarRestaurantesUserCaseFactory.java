package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.BuscarRestaurantesUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.BuscarRestaurantesAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarRestaurantesUserCaseFactory {

    public BuscarRestaurantesUserCase buildBuscarRestauranteUserCase(RestauranteRepository restauranteRepository,
                                                                     RestaurantePresenter restaurantePresenter,
                                                                     BuscarRestaurantesPresenter buscarRestaurantesPresenter) {
        return new BuscarRestaurantesUserCase(
                buildBuscarRestauranteGateway(restauranteRepository, restaurantePresenter),
                buscarRestaurantesPresenter
        );
    }

    private static BuscarRestaurantesAdapter buildBuscarRestauranteGateway(RestauranteRepository restauranteRepository,
                                                                           RestaurantePresenter restaurantePresenter) {
        return new BuscarRestaurantesAdapter(restauranteRepository, restaurantePresenter);
    }

}
