package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservaPorIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados.getReserva;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletaReservaPorIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private DeletaReservaPorIdUserCase deletaReservaPorIdUserCase;

    @Mock
    private DeletaReservaPorIdGateway deletaReservaPorIdGateway;

    @Mock
    BuscarReservaPorIdGateway buscarReservaPorIdGateway;

    @Test
    void testaDeletePorId(){

        var id = "66eb8772fd532626f457c740";

        when(buscarReservaPorIdGateway.buscarPorId(id)).thenReturn(Optional.of(getReserva()));

        deletaReservaPorIdUserCase.deletaPorId(id);

        verify(buscarReservaPorIdGateway, times(1)).buscarPorId(id);
        verify(deletaReservaPorIdGateway,times(1)).deletaPorId(id);
    }

    @Test
    void testaDeletePorIdComReservaNaoEncontrada(){

        var id = "66eb8772fd532626f457c740";

        when(buscarReservaPorIdGateway.buscarPorId(id)).thenReturn(Optional.empty());

        assertThrows(ExceptionAdvice.class, () -> deletaReservaPorIdUserCase.deletaPorId(id));

        verify(buscarReservaPorIdGateway,times(1)).buscarPorId(id);
        verify(deletaReservaPorIdGateway,times(0)).deletaPorId(id);
    }


}
