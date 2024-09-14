package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.ListarTodosRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.ListarTodosRestauranteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.ListarTodosRestauranteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ListarTodosRestauranteUserCaseFactory {

    public ListarTodosRestauranteUserCase buildListarTodosRestauranteUserCase(ListarTodosRestaurantePresenter listarTodosRestaurantePresenter,
                                                                              RestaurantePresenter restaurantePresenter,
                                                                              RestauranteRepository restauranteRepository) {
        return new ListarTodosRestauranteUserCase(
                buildListarTodosRestauranteGateway(restaurantePresenter, restauranteRepository),
                listarTodosRestaurantePresenter
        );
    }

    private ListarTodosRestauranteAdapter buildListarTodosRestauranteGateway(RestaurantePresenter restaurantePresenter, RestauranteRepository restauranteRepository) {
        return new ListarTodosRestauranteAdapter(restauranteRepository, restaurantePresenter);
    }

}
