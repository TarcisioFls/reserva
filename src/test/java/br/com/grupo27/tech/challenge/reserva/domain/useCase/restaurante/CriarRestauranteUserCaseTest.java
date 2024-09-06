package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.CriarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteInput;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarRestauranteUserCaseTest {

    private AutoCloseable closeable;
    private CriarRestauranteInput input;
    private Restaurante restaurante;
    private CriarRestauranteOutput expecitativaOutput;

    @Mock
    private CriarRestauranteGateway criarRestauranteGateway;

    @Mock
    private CriarRestaurantePresenter criarRestaurantePresenter;

    @InjectMocks
    private CriarRestauranteUserCase criarRestauranteUserCase;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        input = getCriarRestauranteInput();
        restaurante = getRestaurante();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testCriar() {
        when(criarRestaurantePresenter.criarRestauranteInputParaRestaurante(input)).thenReturn(restaurante);
        when(criarRestauranteGateway.criar(restaurante)).thenReturn(restaurante);
        when(criarRestaurantePresenter.restauranteParaCriarRestauranteOutput(restaurante)).thenReturn(expecitativaOutput);

        var output = criarRestauranteUserCase.criar(input);

        assertEquals(expecitativaOutput, output);
        verify(criarRestaurantePresenter, times(1)).criarRestauranteInputParaRestaurante(input);
        verify(criarRestauranteGateway, times(1)).criar(restaurante);
        verify(criarRestaurantePresenter, times(1)).restauranteParaCriarRestauranteOutput(restaurante);
    }
}