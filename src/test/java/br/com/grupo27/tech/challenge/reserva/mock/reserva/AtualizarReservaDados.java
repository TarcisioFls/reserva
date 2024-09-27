package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.AtualizarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.AtualizarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.AtualizarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface AtualizarReservaDados {

    static AtualizarReservaRequest getAtualizarReservaRequest() {

        return new AtualizarReservaRequest(LocalDateTime.of(2024, 1, 1, 20, 0),
                2,
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb");
    }

    static AtualizarReservaOutput getAtualizarReservaOutput() {

        return new AtualizarReservaOutput("0f55297d-8e66-4914-b22a-4c0e4d646794",
                "66e8e4563ea03c1d35ba65bb",
                "66e39d371994ae7f1b5e9ff0",
                2,
                LocalDateTime.of(2024, 1, 1, 20, 0),
                RESERVADO);
    }

    static Reserva getReserva() {

        return new Reserva("0f55297d-8e66-4914-b22a-4c0e4d646794",
                LocalDateTime.of(2024, 1, 1, 20, 0),
                2,
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                RESERVADO);
    }

    static Reserva getOutraReserva() {

            return new Reserva("1055297d-8e66-4914-b22a-4c0e4d646097",
                    LocalDateTime.of(2024, 1, 1, 20, 0),
                    2,
                    "66e39d371994ae7f1b5e9ff0",
                    "66e8e4563ea03c1d35ba65bb",
                    RESERVADO);
    }

    static Reserva getReservaComHoraNoPassado() {

            return new Reserva("0f55297d-8e66-4914-b22a-4c0e4d646794",
                    LocalDateTime.of(2020, 1, 1, 20, 0),
                    2,
                    "66e39d371994ae7f1b5e9ff0",
                    "66e8e4563ea03c1d35ba65bb",
                    RESERVADO);
    }

    static AtualizarReservaInput getAtualizarReservaInput() {

        return new AtualizarReservaInput("0f55297d-8e66-4914-b22a-4c0e4d646794",
                LocalDateTime.of(2024, 1, 1, 20, 0),
                2,
                "66e8e4563ea03c1d35ba65bb",
                "66e39d371994ae7f1b5e9ff0");
    }


    static ReservaResponse getAtualizarReservaResponse() {

       return new ReservaResponse("0f55297d-8e66-4914-b22a-4c0e4d646794",
                "66e8e4563ea03c1d35ba65bb",
                "66e39d371994ae7f1b5e9ff0",
                2,
                LocalDateTime.of(2024, 1, 1, 20, 0),
                RESERVADO);
    }

    static ReservaModel getReservaModel() {

        return new ReservaModel("0f55297d-8e66-4914-b22a-4c0e4d646794",
                LocalDateTime.of(2024, 1, 1, 20, 0),
                4,
                "66e39d371994ae7f1b5e9ff0",
                "66e8e4563ea03c1d35ba65bb",
                RESERVADO);
    }
}
