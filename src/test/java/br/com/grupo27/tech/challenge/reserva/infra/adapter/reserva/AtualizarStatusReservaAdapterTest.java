package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getReservaCancelada;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getReservaModelCancelada;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtualizarStatusReservaAdapterTest extends TesteConfig {

    @InjectMocks
    private AtualizarStatusReservaAdapter atualizarStatusReservaAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeAtualizarStatus() {
        var reservaCancelada = getReservaCancelada();
        var reservaModel = getReservaModelCancelada();

        when(reservaPresenter.reservaEmReservaModel(reservaCancelada)).thenReturn(reservaModel);
        when(reservaRepository.save(reservaModel)).thenReturn(reservaModel);
        when(reservaPresenter.reservaModelEmReserva(reservaModel)).thenReturn(reservaCancelada);

        atualizarStatusReservaAdapter.atualizarStatus(reservaCancelada);

        verify(reservaPresenter, times(1)).reservaEmReservaModel(reservaCancelada);
        verify(reservaRepository, times(1)).save(reservaModel);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(reservaModel);

    }


}