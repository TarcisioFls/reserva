package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.BuscarProprietarioPorIdOutput;

public interface BuscarProprietarioPorIdPresenter {

    BuscarProprietarioPorIdOutput proprietarioEmBuscarProprietarioPorIdOutput(Proprietario proprietario);

    ProprietarioResponse proprietarioResponseEmBuscarProprietarioPorIdOutput(BuscarProprietarioPorIdOutput buscarProprietarioPorIdOutput);
}
