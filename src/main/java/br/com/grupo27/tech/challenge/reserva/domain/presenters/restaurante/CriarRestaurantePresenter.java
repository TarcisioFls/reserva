package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;

public interface CriarRestaurantePresenter {

    Restaurante criarRestauranteInputParaRestaurante(CriarRestauranteInput criarRestauranteInput);

    CriarRestauranteOutput restauranteParaCriarRestauranteOutput(Restaurante restaurante);

    CriarRestauranteInput criarRestauranteParaCriarRestauranteInput(CriarRestauranteRequest request);

    RestauranteResponse criarRestauranteOutputParaRestauranteResponse(CriarRestauranteOutput restauranteOutput);
}
