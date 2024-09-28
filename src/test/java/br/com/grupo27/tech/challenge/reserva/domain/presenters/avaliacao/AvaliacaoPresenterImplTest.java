package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacao;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModel;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AvaliacaoPresenterImplTest extends TesteConfig {

    private Avaliacao avaliacao;
    private AvaliacaoModel avaliacaoModel;

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private AvaliacaoPresenterImpl avaliacaoPresenter;

    @BeforeEach
    void inicia() {
        avaliacao = getCriarAvaliacao();
        avaliacaoModel = getCriarAvaliacaoModel();
    }

    @Test
    void testeAvaliacaoEmAvaliacaoModel() {
        var resultado = avaliacaoPresenter.avaliacaoEmAvaliacaoModel(avaliacao);

        assertEquals(avaliacaoModel, resultado);
    }

    @Test
    void testeAvaliacaoModelEmAvaliacao() {
        var resultado = avaliacaoPresenter.avaliacaoModelEmAvaliacao(avaliacaoModel);

        assertEquals(avaliacao, resultado);
    }

    @Test
    void testePageAvaliacaoModelEmPageAaliacao() {
        var pageAvaliacaoModel = getPageAvaliacaoModel();

        var pageAvaliacao = avaliacaoPresenter.pageAvaliacaoModelListEmPageAvaliacaoList(pageAvaliacaoModel);

        assertEquals(2, pageAvaliacao.getContent().size());
        var avaliacao1 = pageAvaliacao.getContent().getFirst();
        var avaliacao2 = pageAvaliacao.getContent().get(1);

        assertEquals(ID_AVALIACAO1, avaliacao1.getId());
        assertEquals(NOTA1, avaliacao1.getNota());
        assertEquals(COMENTARIO1, avaliacao1.getComentario());
        assertEquals(ID_RESERVA1, avaliacao1.getReservaId());

        assertEquals(ID_AVALIACAO2, avaliacao2.getId());
        assertEquals(NOTA2, avaliacao2.getNota());
        assertEquals(COMENTARIO2, avaliacao2.getComentario());
        assertEquals(ID_RESERVA2, avaliacao2.getReservaId());
    }
}