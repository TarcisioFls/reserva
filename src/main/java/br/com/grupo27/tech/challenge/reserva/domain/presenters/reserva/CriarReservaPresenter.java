package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.CriarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.CriarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.CriarReservaOutput;

public interface CriarReservaPresenter {

    CriarReservaInput criarReservaRequestEmCriarReservaInput(CriarReservaRequest request);

    ReservaResponse criarReservaOutputEmReservaResponse(CriarReservaOutput criarReservaOutput);

    Reserva criarReservaInputEmReserva(CriarReservaInput criarReservaInput);

    CriarReservaOutput reservaEmCriarReservaOutput(Reserva reserva);
}
