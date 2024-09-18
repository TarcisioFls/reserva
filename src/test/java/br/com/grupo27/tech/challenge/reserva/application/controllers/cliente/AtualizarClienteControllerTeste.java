package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.AtualizarClienteController;
import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.AtualizarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.AtualizarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.AtualizarClienteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.AtualizarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.AtualizarClienteUserCase;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.AtualizarClienteDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteAtualizadoResponse;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtualizarClienteController.class)
public class AtualizarClienteControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteUserCaseFactory clienteUserCaseFactory;

    @MockBean
    private AtualizarClientePresenter atualizarClientePresenter;

    @MockBean
    private ClientePresenter clientePresenter;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private AtualizarClienteUserCase atualizarClienteUserCase;

    //TODO: ajustar teste
//    @Test
    void testeAtualizar() throws Exception{

        when(clienteUserCaseFactory.buidAtualizarClienteUserCase(atualizarClientePresenter,clientePresenter, clienteRepository))
                .thenReturn(atualizarClienteUserCase);

        when(atualizarClientePresenter.atualizarClienteRequestEmAtualizarClienteInput(any(), any()))
                .thenReturn(getAtualizarClienteInput());

        when(atualizarClienteUserCase.atualizar(any())).thenReturn(getAtualizarClienteOutput());

        when(atualizarClientePresenter.atualizarClienteOutputEmClienteResponse(any()))
                .thenReturn(getClienteAtualizadoResponse());

        mockMvc.perform(put("/clientes/66c67aa035ed1f735450b7a2")
                .contentType("application/json")
                .content(asJsonString(getAtualizarClienteRequest())))
                .andExpect(status().isOk());

        verify(atualizarClientePresenter, times(1)).atualizarClienteRequestEmAtualizarClienteInput(anyString(), any(AtualizarClienteRequest.class));
        verify(atualizarClienteUserCase, times(1)).atualizar(any(AtualizarClienteInput.class));
        verify(atualizarClientePresenter, times(1)).atualizarClienteOutputEmClienteResponse(any(AtualizarClienteOutput.class));
    }

    private static String asJsonString(final Object obj) throws JsonProcessingException{

        return new ObjectMapper().writeValueAsString(obj);
    }


}