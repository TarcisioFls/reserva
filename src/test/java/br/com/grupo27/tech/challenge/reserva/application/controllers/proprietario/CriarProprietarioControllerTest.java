package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.CriarProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.CriarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CriarProprietarioController.class)
class CriarProprietarioControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CriarProprietarioPresenter criarProprietarioPresenter;

    @MockBean
    private CriarProprietarioUserCaseFactory criarProprietarioUserCaseFactory;

    @MockBean
    private ProprietarioPresenter proprietarioPresenter;

    @MockBean
    private ProprietarioRepository proprietarioRepository;

    @MockBean
    private CriarProprietarioUserCase criarProprietarioUserCase;

    @Test
    void testeCriar() throws Exception {

        when(criarProprietarioUserCaseFactory.buildCriarProprietarioUserCase(criarProprietarioPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(criarProprietarioUserCase);

        when(criarProprietarioPresenter.criarProprietarioEmCriarProprietarioInput(any()))
                .thenReturn(getCriarProprietarioInput());

        when(criarProprietarioUserCase.criar(any()))
                .thenReturn(getCriarProprietarioOutput());

        when(criarProprietarioPresenter.criarProprietarioOutputEmProprietarioResponse(any()))
                .thenReturn(getProprietarioResponse());

        mockMvc.perform(post("/proprietarios")
                        .contentType("application/json")
                        .content(asJsonString(getCriarProprietarioRequest())))
                .andExpect(status().isOk());

        verify(criarProprietarioPresenter, times(1)).criarProprietarioEmCriarProprietarioInput(getCriarProprietarioRequest());
        verify(criarProprietarioUserCase, times(1)).criar(getCriarProprietarioInput());
        verify(criarProprietarioPresenter, times(1)).criarProprietarioOutputEmProprietarioResponse(getCriarProprietarioOutput());
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(obj);
    }

}