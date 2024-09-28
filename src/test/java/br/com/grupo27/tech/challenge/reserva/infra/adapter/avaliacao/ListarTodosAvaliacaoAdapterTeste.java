package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.getPageAvaliacaoModel;
import static org.mockito.Mockito.*;

class ListarTodosAvaliacaoAdapterTeste extends TesteConfig {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @InjectMocks
    private ListarTodosAvaliacaoAdapter listarTodosAvaliacaoAdapter;

    @Test
    void testeListarTodosComSucesso() {
        var pageRequest = PageRequest.of(0, 10);
        var pageAvaliacaoModel = getPageAvaliacaoModel();
        var pageAvaliacao = getPageAvaliacao();

        when(avaliacaoRepository.findAll(pageRequest)).thenReturn(pageAvaliacaoModel);
        when(avaliacaoPresenter.pageAvaliacaoModelListEmPageAvaliacaoList(pageAvaliacaoModel)).thenReturn(pageAvaliacao);

        listarTodosAvaliacaoAdapter.listarTodos(pageRequest);

        verify(avaliacaoRepository, times(1)).findAll(pageRequest);
        verify(avaliacaoPresenter, times(1)).pageAvaliacaoModelListEmPageAvaliacaoList(pageAvaliacaoModel);
    }
}