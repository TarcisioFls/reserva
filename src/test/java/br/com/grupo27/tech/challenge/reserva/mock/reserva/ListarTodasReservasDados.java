package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarTodasReservasOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface ListarTodasReservasDados {

    static Page<ReservaModel> getPageReservaModel() {

        var primeiraReservaModel = new ReservaModel("66c67aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "restauranteId", "primeiroClienteId", RESERVADO);
        var segundaReservaModel = new ReservaModel("77b11aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66e39d371994ae7f1b5e9ff0", "segundoClienteId", RESERVADO);
        var reservaModelList = List.of(primeiraReservaModel, segundaReservaModel);

        return new PageImpl<>(reservaModelList);
    }

    static PagedModel<Reserva> getPageReserva() {

        var primeiraReserva = new Reserva("66c67aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "restauranteId", "primeiroClienteId", RESERVADO);
        var segundaReserva = new Reserva("77b11aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66e39d371994ae7f1b5e9ff0", "segundoClienteId", RESERVADO);
        var reservaList = List.of(primeiraReserva, segundaReserva);

        return new PagedModel<>(new PageImpl<>(reservaList));
    }

    static PagedModel<ReservaResponse> getPageReservaResponse() {

        var primeiraReservaResponse = new ReservaResponse("66c67aa035", "restauranteId", "primeiroClienteId",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var segundaReservaResponse = new ReservaResponse("77b11aa035", "66e39d371994ae7f1b5e9ff0", "segundoClienteId",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var reservaResponseList = List.of(primeiraReservaResponse, segundaReservaResponse);

        return new PagedModel<>(new PageImpl<>(reservaResponseList));
    }

    static PagedModel<ListarTodasReservasOutput> getPageListarTodasReservasOutput() {

        var primeiraListarTodasReservasOutput = new ListarTodasReservasOutput("66c67aa035", "primeiroClienteId", "restauranteId",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var segundaListarTodasReservasOutput = new ListarTodasReservasOutput("77b11aa035", "segundoClienteId", "66e39d",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var listarTodasReservasOutputList = List.of(primeiraListarTodasReservasOutput, segundaListarTodasReservasOutput);

        return new PagedModel<>(new PageImpl<>(listarTodasReservasOutputList));
    }
}
