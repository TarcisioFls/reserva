package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelComId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaModelSemId;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getReservaSemId;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarReservaAdapterTeste extends TesteConfig {

    @InjectMocks
    private CriarReservaAdapter criarReservaAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;

    @Test
    void testeCriar() {
        var reservaSemId = getReservaSemId();
        var reservaModelSemId = getReservaModelSemId();
        var reservaModelComId = getReservaModelComId();

        when(reservaPresenter.reservaEmReservaModel(reservaSemId)).thenReturn(reservaModelSemId);
        when(reservaRepository.save(reservaModelSemId)).thenReturn(reservaModelComId);
        when(reservaPresenter.reservaModelEmReserva(reservaModelComId)).thenReturn(getReservaComId());

        criarReservaAdapter.criar(reservaSemId);

        verify(reservaPresenter, times(1)).reservaEmReservaModel(reservaSemId);
        verify(reservaRepository, times(1)).save(reservaModelSemId);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(reservaModelComId);
    }

}