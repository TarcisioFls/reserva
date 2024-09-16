package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import org.springframework.data.web.PagedModel;

public interface BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter {

    PagedModel<RestauranteResponse> buscarRestaurantesOutputEmRestaurantesResponse(PagedModel<BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput> buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutputList);

    PagedModel<BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput> restauranteEmBuscarRestauranteOutput(PagedModel<Restaurante> restaurante);
}
