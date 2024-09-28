package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.factory.cliente.DeletaClientePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.DeletaClientePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeletaClientePorIdController.class)
public class DeletaClientePorIdControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeletaClientePorIdUserCaseFactory deletaClientePorIdUserCaseFactory;

    @MockBean
    private DeletaClientePorIdUserCase deletaClientePorIdUserCase;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ClientePresenter clientePresenter;

    @Test
    void testeDeletePorId() throws Exception {

        var id = "55b67aa035ed1f735450b83b";

        when(deletaClientePorIdUserCaseFactory.buildDeletaClientePorIdUserCase(clientePresenter, clienteRepository))
                .thenReturn(deletaClientePorIdUserCase);

        doNothing().when(deletaClientePorIdUserCase).deletaPorId(id);

        mockMvc.perform(delete("/clientes/{id}", id)).andExpect(status().isNoContent());

        verify(deletaClientePorIdUserCaseFactory, times(1)).buildDeletaClientePorIdUserCase(clientePresenter, clienteRepository);
        verify(deletaClientePorIdUserCase, times(1)).deletaPorId(id);
    }
}
