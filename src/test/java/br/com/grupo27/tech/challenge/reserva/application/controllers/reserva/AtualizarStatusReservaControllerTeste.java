package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.AtualizarStatusReservaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarStatusReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.AtualizarStatusReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.CANCELADO;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getAtualizarReservaOutputCancelado;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.AtualizarStatusReservaDados.getReservaResponseCancelado;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtualizarStatusReservaController.class)
class AtualizarStatusReservaControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtualizarStatusReservaUserCaseFactory atualizarStatusReservaUserCaseFactory;

    @MockBean
    private AtualizarStatusReservaUserCase atualizarStatusReservaUserCase;

    @MockBean
    private ReservaRepository reservaRepository;

    @MockBean
    private ReservaPresenter reservaPresenter;

    @MockBean
    private AtualizarStatusReservaPresenter atualizarStatusReservaPresenter;

    @Test
    void testeAtualizarStatus() throws Exception {

        var id = "0f55297d-8e66-4914-b22a-4c0e4d646794";
        var atualizarReservaOutput = getAtualizarReservaOutputCancelado();
        var reservaResponse = getReservaResponseCancelado();

        when(atualizarStatusReservaUserCaseFactory.buildAtualizarStatusReservaUserCase(
                reservaRepository, reservaPresenter, atualizarStatusReservaPresenter))
                .thenReturn(atualizarStatusReservaUserCase);

        when(atualizarStatusReservaUserCase.atualizarStatus(id, CANCELADO))
                .thenReturn(atualizarReservaOutput);

        when(atualizarStatusReservaPresenter.atualizarReservaOutputEmReservaResponse(atualizarReservaOutput))
                .thenReturn(reservaResponse);

        mockMvc.perform(patch("/reservas/{id}/status/{status}", id, CANCELADO))
                .andExpect(status().isOk());

        verify(atualizarStatusReservaUserCaseFactory, times(1)).buildAtualizarStatusReservaUserCase(
                reservaRepository, reservaPresenter, atualizarStatusReservaPresenter);
        verify(atualizarStatusReservaUserCase, times(1)).atualizarStatus(id, CANCELADO);
        verify(atualizarStatusReservaPresenter, times(1)).atualizarReservaOutputEmReservaResponse(atualizarReservaOutput);

    }

}