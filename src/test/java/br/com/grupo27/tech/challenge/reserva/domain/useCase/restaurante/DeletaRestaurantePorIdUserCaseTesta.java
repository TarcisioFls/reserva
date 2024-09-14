package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantePorIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletaRestaurantePorIdUserCaseTesta extends TesteConfig {

    @InjectMocks
    private DeletaRestaurantePorIdUserCase deletaRestaurantePorIdUserCase;

    @Mock
    private DeletaRestaurantePorIdGateway deletaRestaurantePorIdGateway;

    @Test
    void testeDeletaPorId() {

        var id = "44c67aa035ed1f735450b72a";

//        when(deletaRestaurantePorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getRestaurante()));

        deletaRestaurantePorIdUserCase.deletaPorId(id);

        verify(deletaRestaurantePorIdGateway, times(1)).deletaPorId(id);
//        verify(deletaRestaurantePorIdGateway, times(1)).buscarPorId(id);

    }

//    @Test
//    void testeDeletaPorIdRestauranteNaoEncontrado() {
//        var id = "IdInexistente";
//
//        when(deletaRestaurantePorIdGateway.buscarPorId(id)).thenReturn(empty());
//
//        var resultado = assertThrows(Exception.class, () -> deletaRestaurantePorIdUserCase.deletaPorId(id));
//
//        assertEquals("Restaurante não encontrado", resultado.getMessage());
//
//        verify(deletaRestaurantePorIdGateway, times(1)).buscarPorId(id);
//        verify(deletaRestaurantePorIdGateway, times(0)).deletaRestaurantePorId(id);
//    }
}