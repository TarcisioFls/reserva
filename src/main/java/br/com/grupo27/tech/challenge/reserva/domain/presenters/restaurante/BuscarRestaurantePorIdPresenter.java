package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantePorIdOutput;

public interface BuscarRestaurantePorIdPresenter {

    BuscarRestaurantePorIdOutput restauranteEmBuscarRestaurantePorIdOutput(Restaurante restaurante);

    RestauranteResponse buscarRestaurantePorIdEmRestauranteResponse(BuscarRestaurantePorIdOutput buscarRestaurantePorIdOutput);
}
