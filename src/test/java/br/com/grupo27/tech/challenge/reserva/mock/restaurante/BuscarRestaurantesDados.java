package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;

public interface BuscarRestaurantesDados {

    static PagedModel<BuscarRestaurantesOutput> buscarRestaurantesOutput() {
        var buscarRestaurantesOutput = new BuscarRestaurantesOutput("77b87aa035ed1f735450b9d6", "Akira", "Restaurante", "Rua 1", "08:00", "22:00", 30, List.of(JAPONESA), "66c67aa035ed1f735450b7a2");
        var buscarRestaurantesOutputList = List.of(buscarRestaurantesOutput);
        var pageRequest = PageRequest.of(0, 10);

        var pageBuscarRestaurantesOutput = new PageImpl<>(buscarRestaurantesOutputList, pageRequest, buscarRestaurantesOutputList.size());

        return new PagedModel<>(pageBuscarRestaurantesOutput);
    }

    static PagedModel<RestauranteResponse> buscarRestaurantesResponseList() {
        var restauranteResponse = new RestauranteResponse("77b87aa035ed1f735450b9d6", "Akira", "Restaurante", "Rua 1", "08:00", "22:00", 30, List.of(JAPONESA), "66c67aa035ed1f735450b7a2");
        var restauranteResponseList = List.of(restauranteResponse);
        var pageRequest = PageRequest.of(0, 10);

        var pageRestauranteResponse = new PageImpl<>(restauranteResponseList, pageRequest, restauranteResponseList.size());

        return new PagedModel<>(pageRestauranteResponse);
    }

    static PagedModel<Restaurante> getRestaurante() {
        var restaurante = new Restaurante("77b87aa035ed1f735450b9d6", "Akira", "Restaurante", "Rua 1", "08:00", "22:00", 30, List.of(JAPONESA), "66c67aa035ed1f735450b7a2");
        var pageRequest = PageRequest.of(0, 10);
        var restaurantes = List.of(restaurante);

        var pageRestaurantes = new PageImpl<>(restaurantes, pageRequest, restaurantes.size());

        return new PagedModel<>(pageRestaurantes);
    }

    static Page<RestauranteModel> getRestaurantePage() {
        var restaurante = new RestauranteModel("77b87aa035ed1f735450b9d6", "Akira", "Restaurante", "Rua 1", "08:00", "22:00", 30, List.of(JAPONESA), "66c67aa035ed1f735450b7a2");
        var pageRequest = PageRequest.of(0, 10);
        var restaurantes = List.of(restaurante);

        return new PageImpl<>(restaurantes, pageRequest, restaurantes.size());
    }

}
