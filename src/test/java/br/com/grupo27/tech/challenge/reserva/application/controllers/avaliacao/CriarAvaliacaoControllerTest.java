package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.CriarAvaliacaoUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.CriarAvaliacaoUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarAvaliacaoControllerTest extends TesteConfig {

    @Mock
    private CriarAvaliacaoUserCaseFactory criarAvaliacaoUserCaseFactory;

    @Mock
    private CriarAvaliacaoPresenter criarAvaliacaoPresenter;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private CriarAvaliacaoUserCase criarAvaliacaoUserCase;

    @InjectMocks
    private CriarAvaliacaoController criarAvaliacaoController;

    @Test
    void testeCriarAvaliacaoComSucesso() {

        var request = getCriarAvaliacaoRequest();
        var input = getCriarAvaliacaoInput();
        var output = getCriarAvaliacaoOutput();
        var response = getAvaliacaoResponse();

        when(criarAvaliacaoUserCaseFactory.buildCriarAvaliacaoUserCase(criarAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository))
                .thenReturn(criarAvaliacaoUserCase);
        when(criarAvaliacaoPresenter.criarAvaliacaoRequestEmCriarAvaliacaoInput(request)).thenReturn(input);
        when(criarAvaliacaoUserCase.criar(input)).thenReturn(output);
        when(criarAvaliacaoPresenter.criarAvaliacaoOutputEmAvaliacaoResponse(output)).thenReturn(response);

        var resultado = criarAvaliacaoController.criar(request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(response, resultado.getBody());

        verify(criarAvaliacaoUserCaseFactory, times(1)).buildCriarAvaliacaoUserCase(criarAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);
        verify(criarAvaliacaoPresenter, times(1)).criarAvaliacaoRequestEmCriarAvaliacaoInput(request);
        verify(criarAvaliacaoUserCase, times(1)).criar(input);
        verify(criarAvaliacaoPresenter, times(1)).criarAvaliacaoOutputEmAvaliacaoResponse(output);
    }
}