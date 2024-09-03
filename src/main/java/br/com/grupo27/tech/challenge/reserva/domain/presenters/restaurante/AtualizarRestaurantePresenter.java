package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;

public interface AtualizarRestaurantePresenter {

    AtualizarRestauranteInput atualizarRestauranteRequestParaAtualizarRestauranteInput(String id, AtualizarRestauranteRequest atualizarRestauranteRequest);

    Restaurante atualizarRestauranteInputParaRestaurante(Restaurante restaurante, AtualizarRestauranteInput atualizarRestauranteInput);

    AtualizarRestauranteOutput restauranteParaAtualizarRestauranteOutput(Restaurante restaurante);

    RestauranteResponse atualizarRestauranteOutputParaRestauranteResponse(AtualizarRestauranteOutput atualizarRestauranteOutput);
}
