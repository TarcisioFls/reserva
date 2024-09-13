package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ListarTodosProprietariosPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ListarTodosProprietariosUserCase;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageProprietariosResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.ListarTodosProprietariosDados.getPageTodosProprietariosOutput;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarTodosProprietarioController.class)
class ListarTodosProprietarioControllerTeste extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProprietarioUserCaseFactory proprietarioUserCaseFactory;

    @MockBean
    private ListarTodosProprietariosUserCase listarTodosProprietariosUserCase;

    @MockBean
    private ListarTodosProprietariosPresenter listarTodosProprietariosPresenter;

    @MockBean
    private ProprietarioPresenter proprietarioPresenter;

    @MockBean
    private ProprietarioRepository proprietarioRepository;

    @Test
    void testListarTodos() throws Exception {

        when(proprietarioUserCaseFactory.buildListarTodosProprietariosUserCase(listarTodosProprietariosPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(listarTodosProprietariosUserCase);

        when(listarTodosProprietariosUserCase.listarTodos(0, 10))
                .thenReturn(getPageTodosProprietariosOutput());

        when(listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(getPageTodosProprietariosOutput()))
                .thenReturn(getPageProprietariosResponse());

        mockMvc.perform(get("/proprietarios")
                        .param("pagina", "0")
                        .param("tamanho", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testListarTodosSemPagina() throws Exception {

        when(proprietarioUserCaseFactory.buildListarTodosProprietariosUserCase(listarTodosProprietariosPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(listarTodosProprietariosUserCase);

        when(listarTodosProprietariosUserCase.listarTodos(0, 10))
                .thenReturn(getPageTodosProprietariosOutput());

        when(listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(getPageTodosProprietariosOutput()))
                .thenReturn(getPageProprietariosResponse());

        mockMvc.perform(get("/proprietarios")
                        .param("tamanho", "10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testListarTodosSemTamanho() throws Exception {

        when(proprietarioUserCaseFactory.buildListarTodosProprietariosUserCase(listarTodosProprietariosPresenter, proprietarioPresenter, proprietarioRepository))
                .thenReturn(listarTodosProprietariosUserCase);

        when(listarTodosProprietariosUserCase.listarTodos(0, 50))
                .thenReturn(getPageTodosProprietariosOutput());

        when(listarTodosProprietariosPresenter.pageTodosProprietariosOutputEmPageProprietarioListResponse(getPageTodosProprietariosOutput()))
                .thenReturn(getPageProprietariosResponse());

        mockMvc.perform(get("/proprietarios")
                        .param("pagina", "0"))
                .andExpect(status().isOk());
    }

}