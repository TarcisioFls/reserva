package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarTodasReservasGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarTodasReservasOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarTodasReservasPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.web.PagedModel;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados.getPageListarTodasReservasOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados.getPageReserva;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ListarTodasReservasUserCaseTeste extends TesteConfig {

    @InjectMocks
    private ListarTodasReservasUserCase listarTodasReservasUserCase;

    @Mock
    private ListarTodasReservasGateway listarTodasReservasGateway;

    @Mock
    private ListarTodasReservasPresenter listarTodasReservasPresenter;

    @Test
    void testeListarTodos() {

        var paginacaoReserva = getPageReserva();

        when(listarTodasReservasGateway.listarTodos(any())).thenReturn(paginacaoReserva);
        when(listarTodasReservasPresenter.pageReservaEmPageTodosProprietariosOutput(paginacaoReserva)).thenReturn(getPageListarTodasReservasOutput());

        PagedModel<ListarTodasReservasOutput> resultado = listarTodasReservasUserCase.listarTodos(0, 10);

        assertNotNull(resultado);

        verify(listarTodasReservasGateway, Mockito.times(1)).listarTodos(any());
        verify(listarTodasReservasPresenter, Mockito.times(1)).pageReservaEmPageTodosProprietariosOutput(paginacaoReserva);

    }
}