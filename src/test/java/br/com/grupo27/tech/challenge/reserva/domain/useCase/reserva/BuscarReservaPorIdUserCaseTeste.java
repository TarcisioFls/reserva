package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.BuscarReservaPorIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_NAO_ENCONTRADA;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.BuscarReservaPorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuscarReservaPorIdUserCaseTeste extends TesteConfig {

    @Mock
    private BuscarReservaPorIdGateway buscarReservaPorIdGateway;

    @Mock
    private BuscarReservaPorIdPresenter buscarReservaPorIdPresenter;

    @InjectMocks
    private BuscarReservaPorIdUserCase buscarReservaPorIdUserCase;

    @Test
    void testeBuscarReservaPorIdComSucesso() {
        var reserva = getReserva();
        var expectativaOutput = getBuscarReservaPorIdOutput();

        when(buscarReservaPorIdGateway.buscarPorId(ID)).thenReturn(Optional.of(reserva));
        when(buscarReservaPorIdPresenter.reservaEmBuscarReservaPorIdOutput(reserva)).thenReturn(expectativaOutput);

        var resultado = buscarReservaPorIdUserCase.buscarPorId(ID);

        assertEquals(expectativaOutput, resultado);
        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(ID);
        verify(buscarReservaPorIdPresenter, times(1)).reservaEmBuscarReservaPorIdOutput(reserva);
    }

    @Test
    void testeBuscarReservaPorIdNaoEcontrada() {

        when(buscarReservaPorIdGateway.buscarPorId(ID)).thenReturn(Optional.empty());

        var exceptionAdvice = assertThrows(ExceptionAdvice.class,
                () -> buscarReservaPorIdUserCase.buscarPorId(ID));

        assertEquals(RESERVA_NAO_ENCONTRADA, exceptionAdvice.getCodigoError());
        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(ID);
        verify(buscarReservaPorIdPresenter, never()).reservaEmBuscarReservaPorIdOutput(any());
    }
}