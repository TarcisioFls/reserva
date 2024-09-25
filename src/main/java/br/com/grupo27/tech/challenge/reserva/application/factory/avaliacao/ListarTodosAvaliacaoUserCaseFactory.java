package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarTodosAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.ListarTodosAvaliacaoUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao.ListarTodosAvaliacaoAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ListarTodosAvaliacaoUserCaseFactory {

    public ListarTodosAvaliacaoUserCase buildListarTodosAvaliacaoUserCase(ListarTodosAvaliacaoPresenter listarTodosAvaliacaoPresenter,
                                                                          AvaliacaoPresenter avaliacaoPresenter,
                                                                          AvaliacaoRepository avaliacaoRepository) {
        return new ListarTodosAvaliacaoUserCase(
                buildListarTodosAvaliacaoGateway(avaliacaoPresenter, avaliacaoRepository),
                listarTodosAvaliacaoPresenter
        );
    }

    public ListarTodosAvaliacaoAdapter buildListarTodosAvaliacaoGateway(AvaliacaoPresenter avaliacaoPresenter, AvaliacaoRepository avaliacaoRepository) {
        return new ListarTodosAvaliacaoAdapter(avaliacaoRepository, avaliacaoPresenter);
    }
}
