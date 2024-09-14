package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.AtualizarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.AtualizarRestauranteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.AtualizarRestauranteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AtualizarRestauranteUserCaseFactory {

    public AtualizarRestauranteUserCase buildAtualizarRestauranteUserCase(AtualizarRestaurantePresenter atualizarRestaurantePresenter,
                                                                          RestaurantePresenter restaurantePresenter,
                                                                          RestauranteRepository restauranteRepository) {
        return new AtualizarRestauranteUserCase(
                buildAtualizarRestauranteGateway(restaurantePresenter, restauranteRepository),
                atualizarRestaurantePresenter
        );
    }

    private AtualizarRestauranteAdapter buildAtualizarRestauranteGateway(RestaurantePresenter restaurantePresenter, RestauranteRepository restauranteRepository) {
        return new AtualizarRestauranteAdapter(restauranteRepository, restaurantePresenter);
    }
}
