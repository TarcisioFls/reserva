package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.AtualizarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.AtualizarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AtualizarReservaPresenterImpl implements AtualizarReservaPresenter {

    private final ModelMapper mapper;

    @Override
    public AtualizarReservaInput atualizarReservaRequestEmAtualizarReservaInput(String id, AtualizarReservaRequest request) {

        return new AtualizarReservaInput(id, request.getDataHora(), request.getQuantidadePessoas(),
                request.getClienteId(), request.getRestauranteId());
    }


    @Override
    public ReservaResponse atualizarReservaOutputEmReservaResponse(AtualizarReservaOutput atualizarReservaOutput) {

        return mapper.map(atualizarReservaOutput, ReservaResponse.class);
    }

    @Override
    public AtualizarReservaOutput reservaEmAtualizarReservaOutput(Reserva reserva) {

        return mapper.map(reserva, AtualizarReservaOutput.class);
    }

    @Override
    public Reserva atualizarReservaInputEmReserva(Reserva reserva, AtualizarReservaInput atualizarReservaInput) {
        mapper.map(atualizarReservaInput, reserva);

        return reserva;
    }
}
