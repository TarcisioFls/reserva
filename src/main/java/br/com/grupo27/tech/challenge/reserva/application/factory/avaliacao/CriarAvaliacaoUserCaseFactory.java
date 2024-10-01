package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.CriarAvaliacaoUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao.CriarAvaliacaoAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservaPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CriarAvaliacaoUserCaseFactory {

    public CriarAvaliacaoUserCase buildCriarAvaliacaoUserCase(CriarAvaliacaoPresenter criarAvaliacaoPresenter,
                                                              AvaliacaoPresenter avaliacaoPresenter,
                                                              AvaliacaoRepository avaliacaoRepository,
                                                              ReservaRepository reservaRepository,
                                                              ReservaPresenter reservaPresenter) {
        return new CriarAvaliacaoUserCase(
                buildCriarAvaliacaoGateway(avaliacaoPresenter, avaliacaoRepository),
                buildBuscarReservaPorIdAdapter(reservaRepository, reservaPresenter),
                criarAvaliacaoPresenter
        );
    }

    public CriarAvaliacaoAdapter buildCriarAvaliacaoGateway(AvaliacaoPresenter avaliacaoPresenter, AvaliacaoRepository avaliacaoRepository) {
        return new CriarAvaliacaoAdapter(avaliacaoRepository, avaliacaoPresenter);
    }

    public BuscarReservaPorIdAdapter buildBuscarReservaPorIdAdapter(ReservaRepository reservaRepository, ReservaPresenter reservaPresenter) {
        return new BuscarReservaPorIdAdapter(reservaRepository, reservaPresenter);
    }
}
