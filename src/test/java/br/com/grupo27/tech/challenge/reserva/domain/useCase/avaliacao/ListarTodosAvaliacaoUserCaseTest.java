package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarTodosAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacaoOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ListarTodosAvaliacaoUserCaseTest extends TesteConfig {

    @Mock
    private ListarTodosAvaliacaoGateway listarTodosAvaliacaoGateway;

    @Mock
    private ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter;

    @InjectMocks
    private ListarTodosAvaliacaoUserCase listarTodosAvaliacaoUserCase;

    @Test
    void testeListarTodosComSucesso() {
        int pagina = 0;
        int tamanho = 10;
        var pageRequest = PageRequest.of(pagina, tamanho);

        var pageAvaliacao = getPageAvaliacao();
        var pageAvaliacaoOutput = getPageAvaliacaoOutput();

        when(listarTodosAvaliacaoGateway.listarTodos(pageRequest)).thenReturn(pageAvaliacao);
        when(listarTodosAvaliacaoPresenter.pageAvaliacaoEmPageTodosAvaliacaoOutput(pageAvaliacao)).thenReturn(pageAvaliacaoOutput);

        var resultado = listarTodosAvaliacaoUserCase.listarTodos(pagina, tamanho);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), pageAvaliacaoOutput.getContent().getFirst());
        verify(listarTodosAvaliacaoGateway, times(1)).listarTodos(pageRequest);
        verify(listarTodosAvaliacaoPresenter, times(1)).pageAvaliacaoEmPageTodosAvaliacaoOutput(pageAvaliacao);
    }
}