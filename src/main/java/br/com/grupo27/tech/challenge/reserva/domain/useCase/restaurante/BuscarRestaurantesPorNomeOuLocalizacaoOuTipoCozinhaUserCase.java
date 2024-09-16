package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase {

    private final BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway;

    private final BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;

    public PagedModel<BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput> buscarRestaurantes(String buscar) {

        var restauranteList = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway.buscar(buscar);

        return buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter.restauranteEmBuscarRestauranteOutput(restauranteList);


    }
}
