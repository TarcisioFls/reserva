package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.CriarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.CriarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.CriarProprietarioOutput;

public interface CriarProprietarioPresenter {

    Proprietario criarProprietarioInputEmProprietario(CriarProprietarioInput criarProprietarioInput);

    CriarProprietarioOutput proprietarioEmCriarProprietarioOutput(Proprietario proprietario);

    CriarProprietarioInput criarProprietarioEmCriarProprietarioInput(CriarProprietarioRequest request);

    ProprietarioResponse criarProprietarioOutputEmProprietarioResponse(CriarProprietarioOutput criarProprietarioOutput);
}
