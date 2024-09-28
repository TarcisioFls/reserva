package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.ListarTodosAvaliacaoUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.ListarTodosAvaliacaoUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacaoOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacaoResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListarTodosAvaliacaoController.class)
class ListarTodosAvaliacaoControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListarTodosAvaliacaoUserCaseFactory factory;

    @MockBean
    private ListarTodosAvaliacaoUserCase userCase;

    @MockBean
    private ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter;

    @MockBean
    private AvaliacaoPresenter avaliacaoPresenter;

    @MockBean
    private AvaliacaoRepository repository;

    private final int paginaPadrao = 0;
    private final int tamanhoPadrao = 10;

    @BeforeEach
    void setUpMocks() {
        var pageOutput = getPageAvaliacaoOutput();
        var pageResponse = getPageAvaliacaoResponse();

        when(factory.buildListarTodosAvaliacaoUserCase(listarTodosAvaliacaoPresenter, avaliacaoPresenter, repository))
                .thenReturn(userCase);
        when(userCase.listarTodos(paginaPadrao, tamanhoPadrao)).thenReturn(pageOutput);
        when(listarTodosAvaliacaoPresenter.pageTodosAvaliacaoOutputEmPageAvaliacaoListResponse(pageOutput))
                .thenReturn(pageResponse);
    }

    @Test
    void testeListarTodos() throws Exception {
        realizarRequisicao("/avaliacoes", "0", "10")
                .andExpect(status().isOk());
    }

    @Test
    void testeListarTodosSemPagina() throws Exception {
        realizarRequisicao("/avaliacoes", null, "10")
                .andExpect(status().isOk());
    }

    @Test
    void testeListarTodosSemTamanho() throws Exception {
        realizarRequisicao("/avaliacoes", "0", null)
                .andExpect(status().isOk());
    }

    private ResultActions realizarRequisicao(String url, String pagina, String tamanho) throws Exception {
        var request = get(url)
                .contentType("application/json");

        if (pagina != null) {
            request = request.param("pagina", pagina);
        }

        if (tamanho != null) {
            request = request.param("tamanho", tamanho);
        }

        return mockMvc.perform(request);
    }
}