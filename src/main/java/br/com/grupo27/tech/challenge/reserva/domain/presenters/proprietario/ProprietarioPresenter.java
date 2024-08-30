package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public interface ProprietarioPresenter {

    ProprietarioModel proprietarioEmProprietarioModel(Proprietario proprietario);

    Proprietario proprietarioModelEmProprietario(ProprietarioModel proprietarioModel);

    PagedModel<Proprietario> pageProprietarioModelListEmPageProprietarioList(Page<ProprietarioModel> pageProprietarioModelList);
}
