package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarStatusReservaPresenterImpl implements AtualizarStatusReservaPresenter {

    private final ModelMapper mapper;

    @Override
    public ReservaResponse atualizarReservaOutputEmReservaResponse(AtualizarReservaOutput atualizarReservaOutput) {

        return mapper.map(atualizarReservaOutput, ReservaResponse.class);
    }

    @Override
    public AtualizarReservaOutput reservaEmAtualizarReservaOutput(Reserva reserva) {

        return mapper.map(reserva, AtualizarReservaOutput.class);
    }
}
