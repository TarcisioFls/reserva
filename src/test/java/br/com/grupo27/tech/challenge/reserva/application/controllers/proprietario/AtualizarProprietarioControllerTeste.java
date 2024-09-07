package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.AtualizarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioResponseAtualizado;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtualizarProprietarioController.class)
class AtualizarProprietarioControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProprietarioUserCaseFactory proprietarioUserCaseFactory;

    @MockBean
    private AtualizarProprietarioPresenter atualizarProprietarioPresenter;

    @MockBean
    private ProprietarioPresenter proprietarioPresenter;

    @MockBean
    private ProprietarioRepository proprietarioRepository;

    @MockBean
    private AtualizarProprietarioUserCase atualizarProprietarioUserCase;

    @Test
    void teste() throws Exception {

        when(proprietarioUserCaseFactory.buildAtualizarProprietarioUserCase(atualizarProprietarioPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(atualizarProprietarioUserCase);

        when(atualizarProprietarioPresenter.atualizarProprietarioRequestEmAtualizarProprietarioInput(any(), any()))
                .thenReturn(getAtualizarProprietarioInput());

        when(atualizarProprietarioUserCase.atualizar(any()))
                .thenReturn(getAtualizarProprietarioOutput());

        when(atualizarProprietarioPresenter.atualizarProprietarioOutputEmProprietarioResponse(any()))
                .thenReturn(getProprietarioResponseAtualizado());

        mockMvc.perform(put("/proprietarios/66c67aa035ed1f735450b7a2")
                .contentType("application/json")
                .content(asJsonString(getAtualizarProprietarioRequest()))
        ).andExpect(status().isOk());

        verify(atualizarProprietarioPresenter, times(1)).atualizarProprietarioRequestEmAtualizarProprietarioInput(anyString(), any(AtualizarProprietarioRequest.class));
        verify(atualizarProprietarioUserCase, times(1)).atualizar(any(AtualizarProprietarioInput.class));
        verify(atualizarProprietarioPresenter, times(1)).atualizarProprietarioOutputEmProprietarioResponse(any(AtualizarProprietarioOutput.class));


    }

    private static String asJsonString(final Object obj) throws JsonProcessingException {

        return new ObjectMapper().writeValueAsString(obj);
    }

}