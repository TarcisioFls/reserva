package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantePorIdOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BuscarRestaurantePorIdPresenterImplTest extends TesteConfig {

    private BuscarRestaurantePorIdOutput buscarRestaurantePorIdOutput;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private BuscarRestaurantePorIdPresenterImpl buscarRestaurantePorIdPresenter;

    @BeforeEach
    void inicia() {
        buscarRestaurantePorIdOutput = getBuscarRestaurantePorIdOutput();
    }

    @Test
    void testRestauranteEmBuscarRestaurantePorIdOutput() {
        var restaurante = getRestaurante();
        var expectativaOutput = buscarRestaurantePorIdOutput;

        var restultado = buscarRestaurantePorIdPresenter.restauranteEmBuscarRestaurantePorIdOutput(restaurante);

        assertEquals(expectativaOutput, restultado);
        verify(mapper, times(1)).map(restaurante, BuscarRestaurantePorIdOutput.class);
    }

    @Test
    void testBuscarRestaurantePorIdOutputEmRestauranteResponse() {
        var expectativaResponse = getRestauranteResponse();

        var resultado = buscarRestaurantePorIdPresenter.buscarRestaurantePorIdOutputEmRestauranteResponse(buscarRestaurantePorIdOutput);

        assertEquals(expectativaResponse, resultado);
        verify(mapper, times(1)).map(buscarRestaurantePorIdOutput, RestauranteResponse.class);
    }
}