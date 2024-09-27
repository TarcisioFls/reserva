package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.ListarTodasReservasUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarTodasReservasGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarTodasReservasPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.ListarTodasReservasUserCase;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ListarTodasReservasDados.getPageReservaResponse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarTodasReservasController.class)
class ListarTodasReservasControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarTodasReservasUserCaseFactory listarTodasReservasUserCaseFactory;

    @MockBean
    private ListarTodasReservasUserCase listarTodasReservasUserCase;

    @MockBean
    private ListarTodasReservasGateway listarTodasReservasGateway;

    @MockBean
    private ListarTodasReservasPresenter listarTodasReservasPresenter;

    @Test
    void testeListatTodos() throws Exception {

        var pageListaTodasReservasOutput = ListarTodasReservasDados.getPageListarTodasReservasOutput();

        when(listarTodasReservasUserCaseFactory.buildListarTodasReservasUserCase(
                listarTodasReservasGateway,
                listarTodasReservasPresenter)
        ).thenReturn(listarTodasReservasUserCase);
        when(listarTodasReservasUserCase.listarTodos(anyInt(), anyInt())).thenReturn(pageListaTodasReservasOutput);
        when(listarTodasReservasPresenter.pageTodasReservasOutputEmPageReservasResponse(pageListaTodasReservasOutput)).thenReturn(getPageReservaResponse());

        mockMvc.perform(get("/reservas")).andExpect(status().isOk());

        verify(listarTodasReservasUserCaseFactory,times(1)).buildListarTodasReservasUserCase(
                listarTodasReservasGateway, listarTodasReservasPresenter);
        verify(listarTodasReservasUserCase,times(1)).listarTodos(anyInt(), anyInt());
        verify(listarTodasReservasPresenter,times(1)).pageTodasReservasOutputEmPageReservasResponse(pageListaTodasReservasOutput);

    }
}