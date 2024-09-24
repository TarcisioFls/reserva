package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriarAvaliacaoUserCaseFactoryTeste extends TesteConfig {

    @Mock
    private CriarAvaliacaoPresenter criarAvaliacaoPresenter;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    private CriarAvaliacaoUserCaseFactory criarAvaliacaoUserCaseFactory;

    @Test
    void testeBuildCriarAvaliacaoUserCase() {
        var resultado = criarAvaliacaoUserCaseFactory.buildCriarAvaliacaoUserCase(criarAvaliacaoPresenter, avaliacaoPresenter, avaliacaoRepository);

        assertNotNull(resultado);
    }

    @Test
    void testeBuildCriarAvaliacaoGateway() {
        var resultado = criarAvaliacaoUserCaseFactory.buildCriarAvaliacaoGateway(avaliacaoPresenter, avaliacaoRepository);

        assertNotNull(resultado);
    }
}