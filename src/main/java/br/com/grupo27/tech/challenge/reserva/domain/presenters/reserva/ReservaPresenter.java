package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;

public interface ReservaPresenter {

    Reserva reservaModelEmReserva(ReservaModel reservaModel);

    ReservaModel reservaEmReservaModel(Reserva reserva);
}
