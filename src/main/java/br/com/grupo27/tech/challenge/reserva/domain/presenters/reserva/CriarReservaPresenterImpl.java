package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.CriarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.CriarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.CriarReservaOutput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

@Component
@AllArgsConstructor
public class CriarReservaPresenterImpl implements CriarReservaPresenter{

    private ModelMapper mapper;

    @Override
    public CriarReservaInput criarReservaRequestEmCriarReservaInput(CriarReservaRequest request) {

        return new CriarReservaInput(request.getDataHora(), request.getQuantidadePessoas(),
                request.getClienteId(), request.getRestauranteId());
    }

    @Override
    public ReservaResponse criarReservaOutputEmReservaResponse(CriarReservaOutput criarReservaOutput) {

        return mapper.map(criarReservaOutput, ReservaResponse.class);
    }

    @Override
    public Reserva criarReservaInputEmReserva(CriarReservaInput criarReservaInput) {

        return new Reserva(criarReservaInput.getId(), criarReservaInput.getDataHora(),
                criarReservaInput.getQuantidadePessoas(), criarReservaInput.getRestauranteId(),
                criarReservaInput.getClienteId(), RESERVADO);
    }

    @Override
    public CriarReservaOutput reservaEmCriarReservaOutput(Reserva reserva) {

        return mapper.map(reserva, CriarReservaOutput.class);
    }
}
