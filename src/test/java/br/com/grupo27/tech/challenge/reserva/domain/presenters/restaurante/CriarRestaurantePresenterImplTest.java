package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarRestaurantePresenterImplTest {

    private AutoCloseable closeable;
    private CriarRestauranteInput criarRestauranteInput;
    private CriarRestauranteOutput criarRestauranteOutput;
    private CriarRestauranteRequest criarRestauranteRequest;
    private RestauranteResponse restauranteResponse;
    private Restaurante restaurante;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private CriarRestaurantePresenterImpl criarRestaurantePresenterImpl;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        criarRestauranteInput = getCriarRestauranteInput();
        criarRestauranteOutput = getCriarRestauranteOutput();
        criarRestauranteRequest = getCriarRestauranteRequest();
        restauranteResponse = getRestauranteResponse();
        restaurante = getRestaurante();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testCriarRestauranteInputParaRestaurante() {
        when(mapper.map(criarRestauranteInput, Restaurante.class)).thenReturn(restaurante);
        var resultado = criarRestaurantePresenterImpl.criarRestauranteInputParaRestaurante(criarRestauranteInput);

        assertEquals(restaurante, resultado);
        verify(mapper, times(1)).map(criarRestauranteInput, Restaurante.class);
    }

    @Test
    void testRestauranteParaCriarRestauranteOutput() {
        when(mapper.map(restaurante, CriarRestauranteOutput.class)).thenReturn(criarRestauranteOutput);
        var resultado = criarRestaurantePresenterImpl.restauranteParaCriarRestauranteOutput(restaurante);

        assertEquals(criarRestauranteOutput, resultado);
        verify(mapper, times(1)).map(restaurante, CriarRestauranteOutput.class);
    }

    @Test
    void testCriarRestauranteParaCriarRestauranteInput() {
        when(mapper.map(criarRestauranteRequest, CriarRestauranteInput.class)).thenReturn(criarRestauranteInput);
        var resultado = criarRestaurantePresenterImpl.criarRestauranteParaCriarRestauranteInput(criarRestauranteRequest);

        assertEquals(criarRestauranteInput, resultado);
        verify(mapper, times(1)).map(criarRestauranteRequest, CriarRestauranteInput.class);
    }

    @Test
    void testCriarRestauranteOutputParaRestauranteResponse() {
        when(mapper.map(criarRestauranteOutput, RestauranteResponse.class)).thenReturn(restauranteResponse);
        var resultado = criarRestaurantePresenterImpl.criarRestauranteOutputParaRestauranteResponse(criarRestauranteOutput);

        assertEquals(restauranteResponse, resultado);
        verify(mapper, times(1)).map(criarRestauranteOutput, RestauranteResponse.class);
    }
}