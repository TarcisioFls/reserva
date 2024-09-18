package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesDados.getRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesDados.getRestaurantePage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarRestaurantesAdapterTeste extends TesteConfig {

    @InjectMocks
    private BuscarRestaurantesAdapter buscarRestaurantesAdapter;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestaurantePresenter restaurantePresenter;

    @Test
    void testeBuscar() {

        var buscar = "Aki";
        var pageRequest = PageRequest.of(0, 50);

        when(restauranteRepository.findByNomeOrLocalizacaoOrTipoCozinhaList(buscar, pageRequest))
                .thenReturn(Optional.of(getRestaurantePage()));
        when(restaurantePresenter.pageRestauranteModelListEmPageRestauranteList(getRestaurantePage()))
                .thenReturn(getRestaurante());

        var resultado = buscarRestaurantesAdapter.buscar(buscar);

        assertNotNull(resultado);

        verify(restauranteRepository, times(1)).findByNomeOrLocalizacaoOrTipoCozinhaList(buscar, pageRequest);
        verify(restaurantePresenter, times(1)).pageRestauranteModelListEmPageRestauranteList(getRestaurantePage());

    }

    @Test
    void testeBuscarSemResultado() {

        var buscar = "Aki";
        var pageRequest = PageRequest.of(0, 50);

        when(restauranteRepository.findByNomeOrLocalizacaoOrTipoCozinhaList(buscar, pageRequest))
                .thenReturn(Optional.empty());

        var resultado = buscarRestaurantesAdapter.buscar(buscar);

        assertNull(resultado);

        verify(restauranteRepository, times(1)).findByNomeOrLocalizacaoOrTipoCozinhaList(buscar, pageRequest);
        verify(restaurantePresenter, times(1)).pageRestauranteModelListEmPageRestauranteList(Mockito.any());

    }

}