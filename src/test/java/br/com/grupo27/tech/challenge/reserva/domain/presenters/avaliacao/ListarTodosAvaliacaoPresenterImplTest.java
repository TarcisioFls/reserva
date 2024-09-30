package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarTodosAvaliacaoOutput;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarTodosAvaliacaoDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ListarTodosAvaliacaoPresenterImplTest extends TesteConfig {

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private ListarTodosAvaliacaoPresenterImpl listarTodosAvaliacaoPresenter;

    @Test
    void testeConverterPageAaliacaoEmPageTodosAvaliacaoOutput() {

        var pageAvaliacao = getPageAvaliacao();
        var pageAvaliacaoOutput = getPageAvaliacaoOutput();

        var avaliacao1 = pageAvaliacao.getContent().getFirst();
        var avaliacaoOutput1 = pageAvaliacaoOutput.getContent().getFirst();

        var resultado = listarTodosAvaliacaoPresenter.pageAvaliacaoEmPageTodosAvaliacaoOutput(pageAvaliacao);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), avaliacaoOutput1);
        verify(mapper, times(1)).map(avaliacao1, ListarTodosAvaliacaoOutput.class);
    }

    @Test
    void testeConverterPageTodosAvaliacaoOutputEmPageAvaliacaoResponse() {

        var pageAvaliacaoOutput = getPageAvaliacaoOutput();
        var avaliacaoResponse = getPageAvaliacaoResponse().getContent().getFirst();
        var avaliacaoOutput = getPageAvaliacaoOutput().getContent().getFirst();

        var resultado = listarTodosAvaliacaoPresenter.pageTodosAvaliacaoOutputEmPageAvaliacaoListResponse(pageAvaliacaoOutput);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), avaliacaoResponse);
        verify(mapper, times(1)).map(avaliacaoOutput, AvaliacaoResponse.class);
    }
}