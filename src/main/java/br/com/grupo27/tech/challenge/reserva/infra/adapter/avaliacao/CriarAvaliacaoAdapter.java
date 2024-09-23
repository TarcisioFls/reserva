package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.CriarAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarAvaliacaoAdapter implements CriarAvaliacaoGateway {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AvaliacaoPresenter avaliacaoPresenter;

    @Override
    public Avaliacao criar(Avaliacao avaliacao) {
        var avaliacaoModel = avaliacaoPresenter.avaliacaoEmAvaliacaoModel(avaliacao);
        avaliacaoRepository.save(avaliacaoModel);
        avaliacao = avaliacaoPresenter.avaliacaoModelEmAvaliacao(avaliacaoModel);

        return avaliacao;
    }
}
