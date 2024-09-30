package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorClienteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorClienteIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getListarReservasPorClienteIdOutputPagedModel;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getListarReservasPorClienteInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorClienteIdDados.getReservaPageList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ListarReservasPorClienteIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorClienteIdUserCase listarReservasPorClienteIdUserCase;

    @Mock
    private ListarReservasPorClienteIdGateway listarReservasPorClienteIdGateway;

    @Mock
    private ListarReservasPorClienteIdPresenter listarReservasPorClienteIdPresenter;

    @Test
    void testeBuscarPorClienteId() {

        Mockito.when(listarReservasPorClienteIdGateway.listarPorClienteId(getListarReservasPorClienteInput())).thenReturn(getReservaPageList());
        Mockito.when(listarReservasPorClienteIdPresenter.pageReservaModelEmPageReservaOutput(getReservaPageList())).thenReturn(getListarReservasPorClienteIdOutputPagedModel());

        listarReservasPorClienteIdUserCase.buscarPorClienteId(getListarReservasPorClienteInput());

        verify(listarReservasPorClienteIdGateway, times(1)).listarPorClienteId(getListarReservasPorClienteInput());
        verify(listarReservasPorClienteIdPresenter, times(1)).pageReservaModelEmPageReservaOutput(getReservaPageList());

    }
}