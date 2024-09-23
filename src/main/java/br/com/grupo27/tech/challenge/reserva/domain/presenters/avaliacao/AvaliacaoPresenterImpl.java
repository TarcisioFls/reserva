package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvaliacaoPresenterImpl implements AvaliacaoPresenter {

    private final ModelMapper mapper;

    @Override
    public AvaliacaoModel avaliacaoEmAvaliacaoModel(Avaliacao avaliacao) {

        return new AvaliacaoModel(avaliacao.getId(), avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getReservaId());
    }

    @Override
    public Avaliacao avaliacaoModelEmAvaliacao(AvaliacaoModel avaliacaoModel) {

        return new Avaliacao(avaliacaoModel.getId(), avaliacaoModel.getNota(), avaliacaoModel.getComentario(), avaliacaoModel.getReservaId());
    }

    @Override
    public PagedModel<Avaliacao> pageAvaliacaoModelListEmPageAvaliacaoList(Page<AvaliacaoModel> pageAvaliacaoModel) {
        var avaliacoes = pageAvaliacaoModel.map(this::avaliacaoModelEmAvaliacao);

        return new PagedModel<>(avaliacoes);
    }
}
