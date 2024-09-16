package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.getRestaurante;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseTeste extends TesteConfig {

    @InjectMocks
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCase buscarRestaurantesUserCase;

    @Mock
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaGateway buscarRestaurantesGateway;

    @Mock
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter buscarRestaurantesPresenter;


    @Test
    void testeBuscarRestaurantes() {
        var buscar = "buscar";

        when(buscarRestaurantesGateway.buscar(buscar)).thenReturn(getRestaurante());

        when(buscarRestaurantesPresenter.restauranteEmBuscarRestauranteOutput(any())).thenReturn(buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput());

        var resultado = buscarRestaurantesUserCase.buscarRestaurantes(buscar);

        assertNotNull(resultado);

        verify(buscarRestaurantesGateway, times(1)).buscar(buscar);
        verify(buscarRestaurantesPresenter, times(1)).restauranteEmBuscarRestauranteOutput(any());
    }

    @Test
    void testeBuscarRestaurantesSemResultado() {
        var buscar = "buscar";

        when(buscarRestaurantesGateway.buscar(buscar)).thenReturn(null);

        when(buscarRestaurantesPresenter.restauranteEmBuscarRestauranteOutput(any())).thenReturn(null);

        var resultado = buscarRestaurantesUserCase.buscarRestaurantes(buscar);

        assertNull(resultado);

        verify(buscarRestaurantesGateway, times(1)).buscar(buscar);
        verify(buscarRestaurantesPresenter, times(1)).restauranteEmBuscarRestauranteOutput(any());
    }
}