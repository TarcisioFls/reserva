package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verifyNoInteractions;

class ListarTodosAvaliacaoUserCaseFactoryTeste extends TesteConfig {

    @Mock
    private ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private ListarTodosAvaliacaoUserCaseFactory listarTodosAvaliacaoUserCaseFactory;

    @Test
    void testeBuildListarTodosAvaliacaoUserCase() {

        var resultado = listarTodosAvaliacaoUserCaseFactory.buildListarTodosAvaliacaoUserCase(listarTodosAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);

        assertNotNull(resultado);
        verifyNoInteractions(listarTodosAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);
    }

    @Test
    void testeBuildListarTodosAvaliacaoGateway() {

        var resultado = listarTodosAvaliacaoUserCaseFactory.buildListarTodosAvaliacaoGateway(avaliacaoPresenter, avaliacaoRepository);

        assertNotNull(resultado);
        verifyNoInteractions(listarTodosAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);
    }
}