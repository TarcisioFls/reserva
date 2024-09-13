package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantePresenterImplTest {

    private Restaurante restaurante;
    private RestauranteModel restauranteModel;
    private AutoCloseable closeable;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private RestaurantePresenterImpl restaurantePresenter;

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
    void testRestauranteParaRestauranteModel() {
        when(mapper.map(restaurante, RestauranteModel.class)).thenReturn(restauranteModel);
        var resultado = restaurantePresenter.restauranteParaRestauranteModel(restaurante);

        assertEquals(restauranteModel, resultado);
    }

    @Test
    void testRestauranteModelParaRestaurante() {
        when(mapper.map(restauranteModel, Restaurante.class)).thenReturn(restaurante);
        var resultado = restaurantePresenter.restauranteModelParaRestaurante(restauranteModel);

        assertEquals(restaurante.getNome(), resultado.getNome());
    }
}