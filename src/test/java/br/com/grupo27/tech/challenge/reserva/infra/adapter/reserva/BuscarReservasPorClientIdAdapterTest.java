package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaSemId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscarReservasPorClientIdAdapterTest extends TesteConfig {

    @InjectMocks
    private BuscarReservasPorClientIdAdapter buscarReservasPorClientIdAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeBuscarPorClientIdEStatusReservadoEDataHora() {
        var reserva = getReservaSemId();
        var inicioDoDia = reserva.getDataHora().toLocalDate().atStartOfDay();
        var fimDoDia = reserva.getDataHora().toLocalDate().atTime(LocalTime.MAX);
        var reservaModelList = List.of(getReservaModelComId());

        when(reservaRepository.findByClienteIdAndStatusAndDataHoraBetween(reserva.getClienteId(), RESERVADO, inicioDoDia, fimDoDia))
                .thenReturn(reservaModelList);
        when(reservaPresenter.reservaModelEmReserva(any())).thenReturn(CriarReservaDados.getReservaComId());

        var resultado = buscarReservasPorClientIdAdapter.buscarPorClientIdEStatusReservadoEDataHora(reserva, RESERVADO);

        verify(reservaRepository, times(1))
                .findByClienteIdAndStatusAndDataHoraBetween(reserva.getClienteId(), RESERVADO, inicioDoDia, fimDoDia);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(any());

        assertNotNull(resultado);
    }

}