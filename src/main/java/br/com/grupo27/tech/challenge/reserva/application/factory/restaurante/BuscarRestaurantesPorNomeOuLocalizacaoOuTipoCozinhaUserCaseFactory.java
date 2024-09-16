package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory {

    public BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase buildBuscarRestauranteUserCase(RestauranteRepository restauranteRepository,
                                                                                                      RestaurantePresenter restaurantePresenter,
                                                                                                      BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter) {
        return new BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase(
                buildBuscarRestauranteGateway(restauranteRepository, restaurantePresenter),
                buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter
        );
    }

    private static BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaAdapter buildBuscarRestauranteGateway(RestauranteRepository restauranteRepository,
                                                                                                            RestaurantePresenter restaurantePresenter) {
        return new BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaAdapter(restauranteRepository, restaurantePresenter);
    }

}
