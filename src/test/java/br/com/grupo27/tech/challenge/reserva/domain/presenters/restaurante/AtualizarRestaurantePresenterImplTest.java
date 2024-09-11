package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AtualizarRestaurantePresenterImplTest extends TesteConfig {

    private AtualizarRestauranteInput input;
    private Restaurante restaurante;
    private AtualizarRestauranteOutput output;

    @InjectMocks
    private AtualizarRestaurantePresenterImpl atualizarRestaurantePresenter;

    @Spy
    private ModelMapper mapper;

    @BeforeEach
    void inicia() {
        input = getAtualizarRestauranteInput();
        output = getAtualizarRestauranteOutput();
        restaurante = getRestaurante();
    }

    @Test
    void testAtualizarRestauranteRequestEmAtualizarRestauranteInput() {
        String id = ID_TESTE;
        var request = getAtualizarRestauranteRequest();

        var resultado = atualizarRestaurantePresenter.atualizarRestauranteRequestEmAtualizarRestauranteInput(id, request);

        assertEquals(input, resultado);
        assertEquals(id, resultado.getId());
        verify(mapper, times(1)).map(request, AtualizarRestauranteInput.class);
    }

    @Test
    void testAtualizarRestauranteInputEmRestaurante() {

        var resultado = atualizarRestaurantePresenter.atualizarRestauranteInputEmRestaurante(input);

        assertEquals(restaurante.getNome(), resultado.getNome());
    }

    @Test
    void testRestauranteEmAtualizarRestauranteOutput() {

        var resultado = atualizarRestaurantePresenter.restauranteEmAtualizarRestauranteOutput(restaurante);

        assertEquals(output, resultado);
        verify(mapper, times(1)).map(restaurante, AtualizarRestauranteOutput.class);
    }

    @Test
    void testAtualizarRestauranteOutputEmRestauranteResponse() {

        var response = getRestauranteResponse();

        var resultado = atualizarRestaurantePresenter.atualizarRestauranteOutputEmRestauranteResponse(output);

        assertEquals(response, resultado);
        verify(mapper, times(1)).map(output, RestauranteResponse.class);
    }
}