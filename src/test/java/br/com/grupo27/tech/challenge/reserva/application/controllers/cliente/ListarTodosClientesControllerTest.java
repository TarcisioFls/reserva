package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.factory.cliente.ListarTodosClientesUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ListarTodosClientesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ListarTodosClientesUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ListarTodosClientesDados.getPageClienteResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ListarTodosClientesDados.getPageTodosClientesOutput;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarTodosClienteController.class)
class ListarTodosClientesControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarTodosClientesUserCaseFactory listarTodosClientesUserCaseFactory;

    @MockBean
    private ListarTodosClientesUserCase listarTodosClientesUserCase;

    @MockBean
    private ListarTodosClientesPresenter listarTodosClientesPresenter;

    @MockBean
    private ClientePresenter clientePresenter;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    void testListarTodos() throws Exception {

        when(listarTodosClientesUserCaseFactory.buildListarTodosClientesUserCase(listarTodosClientesPresenter,clientePresenter, clienteRepository))
            .thenReturn(listarTodosClientesUserCase);
        when(listarTodosClientesUserCase.listarTodos(0,10))
                .thenReturn(getPageTodosClientesOutput());
        when(listarTodosClientesPresenter.pageTodosClienteOutputEmPageClienteListResponse(getPageTodosClientesOutput()))
                .thenReturn(getPageClienteResponse());

        mockMvc.perform(get("/clientes")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());

    }

    @Test
    void testListarTodosSemPagina() throws Exception {

        when(listarTodosClientesUserCaseFactory.buildListarTodosClientesUserCase(listarTodosClientesPresenter,clientePresenter, clienteRepository))
                .thenReturn(listarTodosClientesUserCase);
        when(listarTodosClientesUserCase.listarTodos(0,10))
                .thenReturn(getPageTodosClientesOutput());
        when(listarTodosClientesPresenter.pageTodosClienteOutputEmPageClienteListResponse(getPageTodosClientesOutput()))
                .thenReturn(getPageClienteResponse());

        mockMvc.perform(get("/clientes")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testListarTodosSemTamanho() throws Exception {

        when(listarTodosClientesUserCaseFactory.buildListarTodosClientesUserCase(listarTodosClientesPresenter,clientePresenter, clienteRepository))
                .thenReturn(listarTodosClientesUserCase);
        when(listarTodosClientesUserCase.listarTodos(0,10))
                .thenReturn(getPageTodosClientesOutput());
        when(listarTodosClientesPresenter.pageTodosClienteOutputEmPageClienteListResponse(getPageTodosClientesOutput()))
                .thenReturn(getPageClienteResponse());

        mockMvc.perform(get("/clientes")
                        .param("pagina", "0"))
                .andExpect(status().isOk());

    }
}
