package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;

public interface AtualizarProprietarioPresenter {
    AtualizarProprietarioInput atualizarProprietarioEmAtualizarProprietarioInput(String id, AtualizarProprietarioRequest atualizarProprietarioRequest);

    Proprietario atualizarProprietarioInputEmProprietario(Proprietario proprietario, AtualizarProprietarioInput atualizarProprietarioInput);

    AtualizarProprietarioOutput proprietarioEmAtualizarProprietarioOutput(Proprietario proprietario);
}
