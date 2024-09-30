package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.DeletaReservaPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.DeletaReservaPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeletaReservaPorIdController.class)
public class DeletaReservaPorIdControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeletaReservaPorIdUserCaseFactory deletaReservaPorIdUserCaseFactory;

    @MockBean
    private DeletaReservaPorIdUserCase deletaReservaPorIdUserCase;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private ReservaPresenter reservaPresenter;

    @Test
    void testaDeletaPorId() throws Exception{

        var id = "66eb8772fd532626f457c740";

        when(deletaReservaPorIdUserCaseFactory.buildDeletaReservaPorIdUserCase(reservaPresenter,reservaRepository))
                .thenReturn(deletaReservaPorIdUserCase);

        doNothing().when(deletaReservaPorIdUserCase).deletaPorId(id);

        mockMvc.perform(delete("/reservas/{id}", id))
                .andExpect(status().isNoContent());

        verify(deletaReservaPorIdUserCaseFactory, times(1)).buildDeletaReservaPorIdUserCase(reservaPresenter,reservaRepository);
        verify(deletaReservaPorIdUserCase, times(1)).deletaPorId(id);
    }

}
