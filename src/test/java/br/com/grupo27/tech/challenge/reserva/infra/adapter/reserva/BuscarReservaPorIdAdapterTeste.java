package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.BuscarReservaPorIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class BuscarReservaPorIdAdapterTeste extends TesteConfig {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @InjectMocks
    private BuscarReservaPorIdAdapter buscarReservaPorIdAdapter;

    @Test
    void testeBuscarPorIdComSucesso() {
        var reservaModel = getReservaModel();
        var reserva = getReserva();

        when(reservaRepository.findById(ID)).thenReturn(Optional.of(reservaModel));
        when(reservaPresenter.reservaModelEmReserva(reservaModel)).thenReturn(reserva);

        var resultado = buscarReservaPorIdAdapter.buscarPorId(ID);

        assertTrue(resultado.isPresent());
        assertEquals(reserva, resultado.get());
        verify(reservaRepository, times(1)).findById(ID);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(reservaModel);
    }
}