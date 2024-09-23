package br.com.grupo27.tech.challenge.reserva.domain.useCase.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.CriarAvaliacaoGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao.CriarAvaliacaoInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.CriarAvaliacaoOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.CriarAvaliacaoPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CriarAvaliacaoUserCase {

    private final CriarAvaliacaoGateway criarAvaliacaoGateway;
    private final CriarAvaliacaoPresenter criarAvaliacaoPresenter;

    public CriarAvaliacaoOutput criar(CriarAvaliacaoInput criarAvaliacaoInput) {
        var avaliacao = criarAvaliacaoPresenter.criarAvaliacaoInputEmAvaliacao(criarAvaliacaoInput);
        avaliacao = criarAvaliacaoGateway.criar(avaliacao);

        return criarAvaliacaoPresenter.avaliacaoEmCriarAvaliacaoOutput(avaliacao);
    }
}
