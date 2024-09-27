package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.CriarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.CriarReservaInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.CriarReservaOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface CriarReservaDados {

    static Reserva getReservaComId() {

        return new Reserva("66eb8772fd532626f457c740", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static Reserva getReservaSemId() {

        return new Reserva(LocalDateTime.of(2024,1,1,20,0), 2,
                "66e39d371994ae7f1b5e9ff0", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static List<Reserva> getReservasDiferentes() {
        var reserva1 = new Reserva("77ab8882fd752626g657c871", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "99b3456d371344ab6f1y1e9pad", RESERVADO);
        var reserva2 = new Reserva("88bc9993fd862727h758d982", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "fb008a04-4040-4c70-80ce-693d58f8939f", RESERVADO);

        return List.of(reserva1, reserva2);
    }

    static CriarReservaInput getCriarReservaInput() {

        return new CriarReservaInput(LocalDateTime.of(2024,1,1,20,0), 2,
                "66e8e4563ea03c1d35ba65bb", "66e39d371994ae7f1b5e9ff0");
    }

    static CriarReservaOutput getCriarReservaOutput() {
        var reservaComId = getReservaComId();

        return new CriarReservaOutput(reservaComId.getId(), reservaComId.getClienteId(), reservaComId.getRestauranteId(),
                reservaComId.getQuantidadePessoas(), reservaComId.getDataHora(), reservaComId.getStatus());
    }

    static ReservaModel getReservaModelSemId() {

        return new ReservaModel(LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static ReservaModel getReservaModelComId() {

        return new ReservaModel("66eb8772fd532626f457c740", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static CriarReservaRequest getCriarReservaRequest() {

        return new CriarReservaRequest(LocalDateTime.of(2024, 1, 1, 20, 0), 2,
                "66e39d371994ae7f1b5e9ff0", "66e8e4563ea03c1d35ba65bb");
    }

    static ReservaResponse getReservaResponse() {
        var reservaComId = getReservaComId();

        return new ReservaResponse(reservaComId.getId(), reservaComId.getClienteId(), reservaComId.getRestauranteId(),
                reservaComId.getQuantidadePessoas(), reservaComId.getDataHora(), reservaComId.getStatus());
    }

    static ReservaModel getReservaModelAvaliacao() {

        return new ReservaModel("66c67aa035ed1f735450b7a2", LocalDateTime.of(2024,1,1,20,0), 2,
                "99f89bb046ed2f846561b8b3", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static Reserva getReservaAvaliacao() {

        return new Reserva("99f89bb046ed2f846561b8b3", LocalDateTime.of(2024,1,1,20,0), 2,
                "99f89bb046ed2f846561b8b3", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }
}
