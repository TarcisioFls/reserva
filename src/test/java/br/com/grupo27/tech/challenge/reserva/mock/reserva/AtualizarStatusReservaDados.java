package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.CANCELADO;

public interface AtualizarStatusReservaDados {

    static Reserva getReservaCancelada() {

        return new Reserva("0f55297d-8e66-4914-b22a-4c0e4d646794",
                LocalDateTime.of(2024, 1, 1, 20, 0),
                2,
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                CANCELADO);
    }

    static ReservaModel getReservaModelCancelada() {

        return new ReservaModel("0f55297d-8e66-4914-b22a-4c0e4d646794",
                LocalDateTime.of(2024, 1, 1, 20, 0),
                2,
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                CANCELADO);
    }

    static AtualizarReservaOutput getAtualizarReservaOutputCancelado() {

        return new AtualizarReservaOutput("0f55297d-8e66-4914-b22a-4c0e4d646794",
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                2,
                LocalDateTime.of(2024, 1, 1, 20, 0),
                CANCELADO);
    }

    static ReservaResponse getReservaResponseCancelado() {

        return new ReservaResponse("0f55297d-8e66-4914-b22a-4c0e4d646794",
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                2,
                LocalDateTime.of(2024, 1, 1, 20, 0),
                CANCELADO);
    }
}
