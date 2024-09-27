package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.AtualizarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.AtualizarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;

public interface AtualizarReservaPresenter {

    AtualizarReservaInput atualizarReservaRequestEmAtualizarReservaInput(String id, AtualizarReservaRequest request);

    ReservaResponse atualizarReservaOutputEmReservaResponse(AtualizarReservaOutput atualizarReservaOutput);

    AtualizarReservaOutput reservaEmAtualizarReservaOutput(Reserva reserva);

    Reserva atualizarReservaInputEmReserva(Reserva reserva, AtualizarReservaInput atualizarReservaInput);
}
