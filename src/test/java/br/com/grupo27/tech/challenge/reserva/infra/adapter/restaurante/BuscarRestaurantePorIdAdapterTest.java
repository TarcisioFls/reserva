package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorIdAdapterTest extends TesteConfig {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @InjectMocks
    private BuscarRestaurantePorIdAdapter buscarRestaurantePorIdAdapter;

    @Test
    void testBuscarPorIdComSucesso() {
        var restauranteModel = getRestauranteModel();
        var restaurante = getRestaurante();

        when(restauranteRepository.findById(ID_BUSCAR_TESTE)).thenReturn(Optional.of(restauranteModel));
        when(restaurantePresenter.restauranteModelParaRestaurante(restauranteModel)).thenReturn(restaurante);

        var resultado = buscarRestaurantePorIdAdapter.buscarPorId(ID_BUSCAR_TESTE);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());
        verify(restauranteRepository, times(1)).findById(ID_BUSCAR_TESTE);
        verify(restaurantePresenter, times(1)).restauranteModelParaRestaurante(restauranteModel);
    }
}