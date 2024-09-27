package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados.getPageReservaModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarTodasReservasAdapterTeste extends TesteConfig {

    @InjectMocks
    private ListarTodasReservasAdapter listarTodasReservasAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;


    @Test
    void testeListarTodos() {

        var pageRequest = PageRequest.of(0, 10);
        var pageRequestModel = getPageReservaModel();
        var pageReservaList = ListarTodasReservasDados.getPageReserva();

        when(reservaRepository.findAll(pageRequest)).thenReturn(pageRequestModel);
        when(reservaPresenter.pageReservaModelListEmPageReservaList(pageRequestModel)).thenReturn(pageReservaList);

        var resultado = listarTodasReservasAdapter.listarTodos(pageRequest);

        assertNotNull(resultado);

        verify(reservaRepository, times(1)).findAll(pageRequest);
        verify(reservaPresenter, times(1)).pageReservaModelListEmPageReservaList(pageRequestModel);

    }
}