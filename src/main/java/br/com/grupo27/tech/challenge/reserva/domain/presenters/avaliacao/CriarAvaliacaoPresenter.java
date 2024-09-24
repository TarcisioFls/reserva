package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request.CriarAvaliacaoRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao.CriarAvaliacaoInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.CriarAvaliacaoOutput;

public interface CriarAvaliacaoPresenter {

    Avaliacao criarAvaliacaoInputEmAvaliacao(CriarAvaliacaoInput criarAvaliacaoInput);

    CriarAvaliacaoOutput avaliacaoEmCriarAvaliacaoOutput(Avaliacao avaliacao);

    CriarAvaliacaoInput criarAvaliacaoRequestEmCriarAvaliacaoInput(CriarAvaliacaoRequest criarAvaliacaoRequest);

    AvaliacaoResponse criarAvaliacaoOutputEmAvaliacaoResponse(CriarAvaliacaoOutput criarAvaliacaoOutput);
}
