package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CriarRestauranteAdapterTest {

    private AutoCloseable closeable;
    private Restaurante restaurante;
    private RestauranteModel restauranteModel;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @InjectMocks
    private CriarRestauranteAdapter criarRestauranteAdapter;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        restaurante = getRestaurante();
        restauranteModel = getRestauranteModel();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testCriar() {
        when(restaurantePresenter.restauranteParaRestauranteModel(restaurante)).thenReturn(restauranteModel);
        when(restauranteRepository.save((any(RestauranteModel.class)))).thenReturn(restauranteModel);
        when(restaurantePresenter.restauranteModelParaRestaurante(restauranteModel)).thenReturn(restaurante);

        var resultado = criarRestauranteAdapter.criar(restaurante);

        assertEquals(restaurante, resultado);
        verify(restaurantePresenter, times(1)).restauranteParaRestauranteModel(restaurante);
        verify(restauranteRepository, times(1)).save(restauranteModel);
        verify(restaurantePresenter, times(1)).restauranteModelParaRestaurante(restauranteModel);
    }
}