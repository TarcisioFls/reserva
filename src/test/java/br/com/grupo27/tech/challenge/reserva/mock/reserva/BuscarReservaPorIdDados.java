package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.BuscarReservaPorIdOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface BuscarReservaPorIdDados {

    String ID = "75y56bb035ed1f735450b3d1";
    LocalDateTime DATA_HORA = LocalDateTime.of(2036, 2, 1, 18, 0);
    int QUANTIDADE_PESSOAS = 2;
    String ID_RESTAURANTE = "86Z67CC035ed1f735450b4f2";
    String ID_CLIENTE = "97Y78DD035ed1f735450b5G3";
    ReservaStatus STATUS = RESERVADO;

    static BuscarReservaPorIdOutput getBuscarReservaPorIdOutput() {
        return new BuscarReservaPorIdOutput(ID, DATA_HORA, QUANTIDADE_PESSOAS, ID_RESTAURANTE, ID_CLIENTE, STATUS);
    }

    static ReservaResponse getReservaResponse() {
        return new ReservaResponse(ID, ID_CLIENTE, ID_RESTAURANTE, QUANTIDADE_PESSOAS, DATA_HORA, STATUS);
    }

    static ReservaModel getReservaModel() {
        var reservaModel = new ReservaModel(DATA_HORA, QUANTIDADE_PESSOAS, ID_RESTAURANTE, ID_CLIENTE, STATUS);
        reservaModel.setId(ID);
        return reservaModel;
    }

    static Reserva getReserva() {
        return new Reserva(ID, DATA_HORA, QUANTIDADE_PESSOAS, ID_RESTAURANTE, ID_CLIENTE, STATUS);
    }
}
