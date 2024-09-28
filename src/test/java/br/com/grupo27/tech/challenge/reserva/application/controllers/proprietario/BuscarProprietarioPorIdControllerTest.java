package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.BuscarProprietarioPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.BuscarProprietarioPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.BuscarProprietarioPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_NAO_ENCONTRADO;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.BuscarProprietarioPorIdDados.buscarProprietarioPorIdOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuscarProprietarioPorIdController.class)
class BuscarProprietarioPorIdControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuscarProprietarioPorIdUserCaseFactory buscarProprietarioPorIdUserCaseFactory;

    @MockBean
    private BuscarProprietarioPorIdUserCase buscarProprietarioPorIdUserCase;

    @MockBean
    private BuscarProprietarioPorIdPresenter buscarProprietarioPorIdPresenter;

    @MockBean
    private ProprietarioRepository proprietarioRepository;

    @MockBean
    private ProprietarioPresenter proprietarioPresenter;

    @Test
    void testeBuscaPorId() throws Exception {

        var id = "66c67aa035ed1f735450b7a2";

        when(buscarProprietarioPorIdUserCaseFactory.buildBuscarProprietarioPorIdUserCase(buscarProprietarioPorIdPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(buscarProprietarioPorIdUserCase);

        when(buscarProprietarioPorIdUserCase.buscarPorId(id))
                .thenReturn(buscarProprietarioPorIdOutput());

        when(buscarProprietarioPorIdPresenter.proprietarioResponseEmBuscarProprietarioPorIdOutput(any()))
                .thenReturn(getProprietarioResponse());

        mockMvc.perform(get("/proprietarios/{id}", id))
                .andExpect(status().isOk());

        verify(buscarProprietarioPorIdUserCase, times(1)).buscarPorId(id);
        verify(buscarProprietarioPorIdPresenter, times(1)).proprietarioResponseEmBuscarProprietarioPorIdOutput(any());

    }

    @Test
    void testeBuscaPorIdComIdInexistente() throws Exception {

        var id = "teste";

        when(buscarProprietarioPorIdUserCaseFactory.buildBuscarProprietarioPorIdUserCase(buscarProprietarioPorIdPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(buscarProprietarioPorIdUserCase);

        when(buscarProprietarioPorIdUserCase.buscarPorId(id))
                .thenThrow(new ExceptionAdvice(PROPRIETARIO_NAO_ENCONTRADO));

        mockMvc.perform(get("/proprietarios/{id}", id))
                .andExpect(status().isNotFound());

        verify(buscarProprietarioPorIdUserCase, times(1)).buscarPorId(id);
        verify(buscarProprietarioPorIdPresenter, never()).proprietarioResponseEmBuscarProprietarioPorIdOutput(any());
    }

}