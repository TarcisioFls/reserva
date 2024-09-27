package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public interface ReservaPresenter {

    Reserva reservaModelEmReserva(ReservaModel reservaModel);

    ReservaModel reservaEmReservaModel(Reserva reserva);

    PagedModel<Reserva> pageReservaModelListEmPageReservaList(Page<ReservaModel> pageReservaModelList);

}
