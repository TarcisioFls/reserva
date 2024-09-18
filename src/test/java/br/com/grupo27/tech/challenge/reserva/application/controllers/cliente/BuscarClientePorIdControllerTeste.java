package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.BuscarClientePorIdController;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.BuscarClientePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.BuscarClientePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.BuscarClientePorIdDados.buscarClientePorIdOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuscarClientePorIdController.class)
public class BuscarClientePorIdControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteUserCaseFactory clienteUserCaseFactory;

    @MockBean
    private BuscarClientePorIdUserCase buscarClientePorIdUserCase;

    @MockBean
    private BuscarClientePorIdPresenter buscarClientePorIdPresenter;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ClientePresenter clientePresenter;

    @Test
    void testeBuscarPorId() throws Exception{

        var id = "66c67aa035ed1f735450b7a2";

        when(clienteUserCaseFactory.buildBuscarClientePorIdUserCase(buscarClientePorIdPresenter, clientePresenter, clienteRepository))
                .thenReturn(buscarClientePorIdUserCase);

        when(buscarClientePorIdUserCase.buscarPorId(id))
                .thenReturn(buscarClientePorIdOutput());

        when(buscarClientePorIdPresenter.clienteResponseEmBuscarClientePorIdOutput(any()))
                .thenReturn(getClienteResponse());

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isOk());

        verify(buscarClientePorIdUserCase, times(1)).buscarPorId(id);
        verify(buscarClientePorIdPresenter, times(1)).clienteResponseEmBuscarClientePorIdOutput(any());
    }

    @Test
    void testeBuscaPorIdComIdInexistente() throws Exception {

        var id = "teste";

        when(clienteUserCaseFactory.buildBuscarClientePorIdUserCase(buscarClientePorIdPresenter, clientePresenter, clienteRepository))
                .thenReturn(buscarClientePorIdUserCase);

        when(buscarClientePorIdUserCase.buscarPorId(id))
                .thenThrow(new ExceptionAdvice(CodigoError.CLIENTE_NAO_ENCONTRADO));

        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isNotFound());


        verify(buscarClientePorIdUserCase, times(1)).buscarPorId(id);
        verify(buscarClientePorIdPresenter, never()).clienteResponseEmBuscarClientePorIdOutput(any());




    }
}
