package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.CriarRestauranteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CriarRestauranteUserCaseFactory {

    public CriarRestauranteUserCase buildCriarRestauranteUserCase(CriarRestaurantePresenter criarRestaurantePresenter,
                                                                  RestaurantePresenter restaurantePresenter,
                                                                  RestauranteRepository restauranteRepository) {
        return new CriarRestauranteUserCase(
                buildCriarRestauranteGateway(restaurantePresenter, restauranteRepository),
                criarRestaurantePresenter
        );
    }

    private CriarRestauranteAdapter buildCriarRestauranteGateway(RestaurantePresenter restaurantePresenter, RestauranteRepository restauranteRepository) {
        return new CriarRestauranteAdapter(restauranteRepository, restaurantePresenter);
    }
}
