package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarTodosAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarTodosAvaliacaoAdapter implements ListarTodosAvaliacaoGateway {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoPresenter avaliacaoPresenter;

    @Override
    public PagedModel<Avaliacao> listarTodos(PageRequest pageRequest) {

        var pageAvaliacaoModel = avaliacaoRepository.findAll(pageRequest);

        return avaliacaoPresenter.pageAvaliacaoModelListEmPageAvaliacaoList(pageAvaliacaoModel);
    }
}
