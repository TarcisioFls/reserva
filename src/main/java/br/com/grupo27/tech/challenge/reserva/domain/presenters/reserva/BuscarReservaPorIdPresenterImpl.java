package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.BuscarReservaPorIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarReservaPorIdPresenterImpl implements BuscarReservaPorIdPresenter {

    private final ModelMapper mapper;

    @Override
    public BuscarReservaPorIdOutput reservaEmBuscarReservaPorIdOutput(Reserva reserva) {

        return mapper.map(reserva, BuscarReservaPorIdOutput.class);
    }

    @Override
    public ReservaResponse buscarReservaPorIdOutputEmReservaResponse(BuscarReservaPorIdOutput buscarReservaPorIdOutput) {

        return mapper.map(buscarReservaPorIdOutput, ReservaResponse.class);
    }
}
