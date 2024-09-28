package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CriarAvaliacaoAdapterTest extends TesteConfig {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @InjectMocks
    private CriarAvaliacaoAdapter criarAvaliacaoAdapter;

    @Test
    void testeCriarAvaliacaoComSucesso() {
        var avaliacao = getCriarAvaliacao();
        var avaliacaoModel = getCriarAvaliacaoModel();

        when(avaliacaoPresenter.avaliacaoEmAvaliacaoModel(avaliacao)).thenReturn(avaliacaoModel);
        when(avaliacaoRepository.save(avaliacaoModel)).thenReturn(avaliacaoModel);
        when(avaliacaoPresenter.avaliacaoModelEmAvaliacao(avaliacaoModel)).thenReturn(avaliacao);

        var resultado = criarAvaliacaoAdapter.criar(avaliacao);

        assertEquals(avaliacao, resultado);

        verify(avaliacaoPresenter, times(1)).avaliacaoEmAvaliacaoModel(avaliacao);
        verify(avaliacaoRepository, times(1)).save(avaliacaoModel);
        verify(avaliacaoPresenter, times(1)).avaliacaoModelEmAvaliacao(avaliacaoModel);
    }
}