package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao.CriarAvaliacaoInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.CriarAvaliacaoOutput;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class CriarAvaliacaoPresenterImplTeste extends TesteConfig {

    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private CriarAvaliacaoPresenterImpl criarAvaliacaoPresenter;

    @Test
    void testeAvalicaoEmCriarAvaliacaoOutput() {
        var avaliacao = getCriarAvaliacao();

        var output = criarAvaliacaoPresenter.avaliacaoEmCriarAvaliacaoOutput(avaliacao);

        assertEquals(avaliacao.getId(), output.getId());
        assertEquals(avaliacao.getNota(), output.getNota());
        assertEquals(avaliacao.getComentario(), output.getComentario());
        assertEquals(avaliacao.getReservaId(), output.getReservaId());

        verify(mapper).map(avaliacao, CriarAvaliacaoOutput.class);
    }

    @Test
    void testeCriarAvaliacaoRequestEmCriarAvaliacaoInput() {
        var request = getCriarAvaliacaoRequest();

        var input = criarAvaliacaoPresenter.criarAvaliacaoRequestEmCriarAvaliacaoInput(request);

        assertEquals(request.getNota(), input.getNota());
        assertEquals(request.getComentario(), input.getComentario());
        assertEquals(request.getReservaId(), input.getReservaId());

        verify(mapper).map(request, CriarAvaliacaoInput.class);
    }

    @Test
    void testeCriarAvaliacaoOutputEmAvaliacaoResponse() {
        var output = getCriarAvaliacaoOutput();

        var response = criarAvaliacaoPresenter.criarAvaliacaoOutputEmAvaliacaoResponse(output);

        assertEquals(output.getNota(), response.getNota());
        assertEquals(output.getComentario(), response.getComentario());
        assertEquals(output.getReservaId(), response.getReservaId());

        verify(mapper).map(output, AvaliacaoResponse.class);
    }
}