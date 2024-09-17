package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteAdapterTest extends TesteConfig {

    private Restaurante restaurante;
    private RestauranteModel restauranteModel;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @InjectMocks
    private AtualizarRestauranteAdapter atualizarRestauranteAdapter;

    @BeforeEach
    void inicia() {
        restaurante = getRestaurante();
        restauranteModel = getRestauranteModel();
    }

    @Test
    void testeAtualizarComSucesso() {

        when(restaurantePresenter.restauranteParaRestauranteModel(restaurante)).thenReturn(restauranteModel);
        when(restauranteRepository.save(restauranteModel)).thenReturn(restauranteModel);
        when(restaurantePresenter.restauranteModelParaRestaurante(restauranteModel)).thenReturn(restaurante);

        var resultado = atualizarRestauranteAdapter.atualizar(restaurante);

        assertEquals(restaurante, resultado);
        verify(restaurantePresenter, times(1)).restauranteParaRestauranteModel(restaurante);
        verify(restauranteRepository, times(1)).save(restauranteModel);
        verify(restaurantePresenter, times(1)).restauranteModelParaRestaurante(restauranteModel);
    }

    @Test
    void testeBuscarPorIdComSucesso() {

        when(restauranteRepository.findById(ID_TESTE)).thenReturn(Optional.of(restauranteModel));
        when(restaurantePresenter.restauranteModelParaRestaurante(restauranteModel)).thenReturn(restaurante);

        var resultado = atualizarRestauranteAdapter.buscarPorId(ID_TESTE);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());
        verify(restauranteRepository, times(1)).findById(ID_TESTE);
        verify(restaurantePresenter, times(1)).restauranteModelParaRestaurante(restauranteModel);
    }

    @Test
    void testeBuscarPorIdNaoEncontrado() {

        when(restauranteRepository.findById(ID_TESTE)).thenReturn(Optional.empty());

        var restultado = atualizarRestauranteAdapter.buscarPorId(ID_TESTE);

        assertTrue(restultado.isEmpty());
        verify(restauranteRepository, times(1)).findById(ID_TESTE);
        verify(restaurantePresenter, never()).restauranteModelParaRestaurante(any());
    }
}