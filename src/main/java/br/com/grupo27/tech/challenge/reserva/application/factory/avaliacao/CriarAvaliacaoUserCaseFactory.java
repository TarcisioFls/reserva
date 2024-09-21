package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.CriarAvaliacaoUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao.CriarAvaliacaoAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CriarAvaliacaoUserCaseFactory {

    public CriarAvaliacaoUserCase buildCriarAvaliacaoUserCase(CriarAvaliacaoPresenter criarAvaliacaoPresenter,
                                                              AvaliacaoPresenter avaliacaoPresenter,
                                                              AvaliacaoRepository avaliacaoRepository) {
        return new CriarAvaliacaoUserCase(
                buildCriarAvaliacaoGateway(avaliacaoPresenter, avaliacaoRepository),
                criarAvaliacaoPresenter
        );
    }

    private CriarAvaliacaoAdapter buildCriarAvaliacaoGateway(AvaliacaoPresenter avaliacaoPresenter, AvaliacaoRepository avaliacaoRepository) {
        return new CriarAvaliacaoAdapter(avaliacaoRepository, avaliacaoPresenter);
    }
}
