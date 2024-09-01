package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.AtualizarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.AtualizarProprietarioUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AtualizarProprietarioControllerTeste extends TesteConfig {

    private MockMvc mockMvc;

    @Mock
    private AtualizarProprietarioPresenter atualizarProprietarioPresenter;

    @Mock
    private ProprietarioPresenter proprietarioPresenter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private AtualizarProprietarioUserCase atualizarProprietarioUserCase;

    @BeforeEach
    void start() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new AtualizarProprietarioController(atualizarProprietarioPresenter, proprietarioPresenter, proprietarioRepository)
        ).build();
    }

//    @Test
    void teste() throws Exception {

        when(atualizarProprietarioPresenter.atualizarProprietarioRequestEmAtualizarProprietarioInput(anyString(), any(AtualizarProprietarioRequest.class)))
                .thenReturn(getAtualizarProprietarioInput());

        when(atualizarProprietarioUserCase.atualizar(any(AtualizarProprietarioInput.class)))
                .thenReturn(getAtualizarProprietarioOutput());

        when(atualizarProprietarioPresenter.atualizarProprietarioOutputEmProprietarioResponse(any(AtualizarProprietarioOutput.class)))
                .thenReturn(ProprietarioDados.getProprietarioResponseAtualizado());

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