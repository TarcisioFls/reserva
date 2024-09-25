package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.BuscarReservaPorIdOutput;

public interface BuscarReservaPorIdPresenter {

    BuscarReservaPorIdOutput reservaEmBuscarReservaPorIdOutput(Reserva reserva);

    ReservaResponse buscarReservaPorIdOutputEmReservaResponse(BuscarReservaPorIdOutput buscarReservaPorIdOutput);
}
