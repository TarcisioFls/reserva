package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorRestauranteIdPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getListarReservasPorRestauranteIdInput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getListarReservasPorRestauranteIdOutputPagedModel;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarReservasPorRestauranteIdDados.getPageReserva;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarReservasPorRestauranteIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private ListarReservasPorRestauranteIdUserCase listarReservasPorRestauranteIdUserCase;

    @Mock
    private ListarReservasPorRestauranteIdGateway listarReservasPorRestauranteIdGateway;

    @Mock
    private ListarReservasPorRestauranteIdPresenter listarReservasPorRestauranteIdPresenter;

    @Test
    void testeBuscarPorRestauranteId() {

        var input = getListarReservasPorRestauranteIdInput();

        when(listarReservasPorRestauranteIdGateway.listarPorRestauranteId(input)).thenReturn(getPageReserva());
        when(listarReservasPorRestauranteIdPresenter.pageReservaModelEmPageReservaOutput(getPageReserva())).thenReturn(getListarReservasPorRestauranteIdOutputPagedModel());

        listarReservasPorRestauranteIdUserCase.buscarPorRestauranteId(input);

        verify(listarReservasPorRestauranteIdGateway, times(1)).listarPorRestauranteId(input);
        verify(listarReservasPorRestauranteIdPresenter, times(1)).pageReservaModelEmPageReservaOutput(getPageReserva());

    }
}