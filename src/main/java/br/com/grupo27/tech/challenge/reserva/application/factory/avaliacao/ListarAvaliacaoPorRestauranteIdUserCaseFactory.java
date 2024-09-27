package br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao.ListarAvaliacaoPorRestauranteIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao.ListarAvaliacaoPorRestauranteIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ListarAvaliacaoPorRestauranteIdUserCaseFactory {

    public ListarAvaliacaoPorRestauranteIdUserCase buildListarAvaliacaoPorRestauranteIdUserCase(
            ListarAvaliacaoPorRestauranteIdPresenter listarAvaliacaoPorRestauranteIdPresenter,
            AvaliacaoPresenter avaliacaoPresenter,
            AvaliacaoRepository avaliacaoRepository,
            ReservaPresenter reservaPresenter,
            ReservaRepository reservaRepository
    ){
        return new ListarAvaliacaoPorRestauranteIdUserCase(
                buildListarAvaliacaoPorRestauranteIdGateway(avaliacaoPresenter, avaliacaoRepository, reservaPresenter, reservaRepository),
                listarAvaliacaoPorRestauranteIdPresenter);
    }

    private ListarAvaliacaoPorRestauranteIdAdapter buildListarAvaliacaoPorRestauranteIdGateway(AvaliacaoPresenter avaliacaoPresenter,
                                                                                               AvaliacaoRepository avaliacaoRepository,
                                                                                               ReservaPresenter reservaPresenter,
                                                                                               ReservaRepository reservaRepository)
    {
        return new ListarAvaliacaoPorRestauranteIdAdapter(avaliacaoRepository, avaliacaoPresenter, reservaRepository, reservaPresenter );
    }



}
