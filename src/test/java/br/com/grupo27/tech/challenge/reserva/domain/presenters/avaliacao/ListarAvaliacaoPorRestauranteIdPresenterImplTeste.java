package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarAvaliacaoPorRestauranteIdOutput;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ListarAvaliacaoPorRestauranteIdPresenterImplTeste extends TesteConfig {

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private ListarAvaliacaoPorRestauranteIdPresenterImpl listarAvaliacaoPorRestauranteIdPresenter;

    @Test
    void testaPageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(){

        var pageAvaliacao = getPageAvaliacao();
        var listaAvaliacaoPorRestauranteIdOutput = getPageListarAvaliacaoPorRestauranteIdOutput();

        var avaliacao1 = pageAvaliacao.getContent().getFirst();
        var listaAvaliacaoPorRestauranteIdOutput1 = listaAvaliacaoPorRestauranteIdOutput.getContent().getFirst();

        var resultado = listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoEmListarAvaliacaoPorRestauranteIdOutput(pageAvaliacao);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), listaAvaliacaoPorRestauranteIdOutput1);
        verify(mapper, times(1)).map(avaliacao1, ListarAvaliacaoPorRestauranteIdOutput.class);
    }

    @Test
    void testaPageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(){

        var getPageListarAvaliacaoPorRestauranteOutput = getPageListarAvaliacaoPorRestauranteIdOutput();
        var avaliacaoResponse = getPageListarAvaliacaoPorRestauranteResponse().getContent().getFirst();
        var avaliacaoOutput = getPageListarAvaliacaoPorRestauranteIdOutput().getContent().getFirst();

        var resultado = listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(getPageListarAvaliacaoPorRestauranteOutput);

        assertNotNull(resultado);
        assertEquals(resultado.getContent().getFirst(), avaliacaoResponse);
        verify(mapper, times(1)).map(avaliacaoOutput, AvaliacaoResponse.class);
    }
}
