package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public interface AvaliacaoPresenter {

    AvaliacaoModel avaliacaoEmAvaliacaoModel(Avaliacao avaliacao);

    Avaliacao avaliacaoModelEmAvaliacao(AvaliacaoModel avaliacaoModel);

    PagedModel<Avaliacao> pageAvaliacaoModelListEmPageAvaliacaoList(Page<AvaliacaoModel> pageAvaliacaoModel);
}
