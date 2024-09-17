package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantePorIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarRestaurantePorIdPresenterImpl implements BuscarRestaurantePorIdPresenter {

    private final ModelMapper mapper;


    @Override
    public BuscarRestaurantePorIdOutput restauranteEmBuscarRestaurantePorIdOutput(Restaurante restaurante) {

        return mapper.map(restaurante, BuscarRestaurantePorIdOutput.class);
    }

    @Override
    public RestauranteResponse buscarRestaurantePorIdOutputEmRestauranteResponse(BuscarRestaurantePorIdOutput buscarRestaurantePorIdOutput) {

        return mapper.map(buscarRestaurantePorIdOutput, RestauranteResponse.class);
    }
}
