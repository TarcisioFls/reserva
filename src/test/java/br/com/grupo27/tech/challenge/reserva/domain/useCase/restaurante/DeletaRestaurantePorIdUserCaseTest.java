package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantePorIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestaurante;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletaRestaurantePorIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private DeletaRestaurantePorIdUserCase deletaRestaurantePorIdUserCase;

    @Mock
    private DeletaRestaurantePorIdGateway deletaRestaurantePorIdGateway;

    @Mock
    private BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    @Mock
    private DeletaReservasPorRestauranteIdGateway deletaReservasPorRestauranteIdGateway;


    @Test
    void testeDeletaPorId() {

        var id = "44c67aa035ed1f735450b72a";

        when(buscarRestaurantePorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getRestaurante()));
        doNothing().when(deletaRestaurantePorIdGateway).deletaPorId(id);
        doNothing().when(deletaReservasPorRestauranteIdGateway).deletaPorRestauranteId(id);

        deletaRestaurantePorIdUserCase.deletaPorId(id);

        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(id);
        verify(deletaRestaurantePorIdGateway, times(1)).deletaPorId(id);
        verify(deletaReservasPorRestauranteIdGateway, times(1)).deletaPorRestauranteId(id);

    }

    @Test
    void testeDeletaPorIdRestauranteNaoEncontrado() {
        var id = "IdInexistente";

        when(buscarRestaurantePorIdGateway.buscarPorId(id)).thenReturn(empty());

        var resultado = assertThrows(ExceptionAdvice.class, () -> deletaRestaurantePorIdUserCase.deletaPorId(id));

        assertEquals("Restaurante não encontrado", resultado.getMessage());

        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(id);
        verify(deletaRestaurantePorIdGateway, never()).deletaPorId(id);
        verify(deletaReservasPorRestauranteIdGateway, never()).deletaPorRestauranteId(id);
    }
}