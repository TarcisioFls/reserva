package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantePorIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.getRestaurante;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.ID_BUSCAR_TESTE;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantePorIdDados.getBuscarRestaurantePorIdOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuscarRestaurantePorIdUserCaseTest extends TesteConfig {

    @Mock
    private BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    @Mock
    private BuscarRestaurantePorIdPresenter buscarRestaurantePorIdPresenter;

    @InjectMocks
    private BuscarRestaurantePorIdUserCase buscarRestaurantePorIdUserCase;

    @Test
    void testBuscarRestaurantePorIdComSucesso() {
        var restaurante = getRestaurante();
        var expectativaOutput = getBuscarRestaurantePorIdOutput();

        when(buscarRestaurantePorIdGateway.buscarPorId(ID_BUSCAR_TESTE)).thenReturn(Optional.of(restaurante));
        when(buscarRestaurantePorIdPresenter.restauranteEmBuscarRestaurantePorIdOutput(restaurante)).thenReturn(expectativaOutput);

        var resultado = buscarRestaurantePorIdUserCase.buscarPorId(ID_BUSCAR_TESTE);

        assertEquals(expectativaOutput, resultado);
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(ID_BUSCAR_TESTE);
        verify(buscarRestaurantePorIdPresenter, times(1)).restauranteEmBuscarRestaurantePorIdOutput(restaurante);
    }

    @Test
    void testBuscarRestaurantePorIdNaoEncontrado() {

        when(buscarRestaurantePorIdGateway.buscarPorId(ID_BUSCAR_TESTE)).thenReturn(Optional.empty());

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> buscarRestaurantePorIdUserCase.buscarPorId(ID_BUSCAR_TESTE));

        assertEquals(RESTAURANTE_NAO_ENCONTRADO, exceptionAdvice.getCodigoError());
        verify(buscarRestaurantePorIdGateway, times(1)).buscarPorId(ID_BUSCAR_TESTE);
        verify(buscarRestaurantePorIdPresenter, never()).restauranteEmBuscarRestaurantePorIdOutput(any());
    }
}