package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesOutput;
import org.springframework.data.web.PagedModel;

public interface BuscarRestaurantesPresenter {

    PagedModel<RestauranteResponse> buscarRestaurantesOutputEmRestaurantesResponse(PagedModel<BuscarRestaurantesOutput> buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutputList);

    PagedModel<BuscarRestaurantesOutput> restauranteEmBuscarRestauranteOutput(PagedModel<Restaurante> restaurante);
}
