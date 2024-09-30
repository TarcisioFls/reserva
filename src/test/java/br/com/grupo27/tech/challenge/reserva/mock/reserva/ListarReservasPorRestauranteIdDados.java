package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorRestauranteIdOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface ListarReservasPorRestauranteIdDados {

    static ListarReservasPorRestauranteIdInput getListarReservasPorRestauranteIdInput() {

        return new ListarReservasPorRestauranteIdInput(0, 10, "66c67aa035ed1f735450b7a2");
    }

    static Page<ReservaModel> getPageReservaModel() {

        var primeiraReservaModel = new ReservaModel("66c67aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
        var segundaReservaModel = new ReservaModel("77b11aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66c67aa035ed1f735450b7a2", "77b57d371994ae7f1b5e1od8", RESERVADO);
        var reservaModelList = List.of(primeiraReservaModel, segundaReservaModel);

        return new PageImpl<>(reservaModelList);
    }

    static PagedModel<Reserva> getPageReserva() {

        var primeiraReserva = new Reserva("66c67aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
        var segundaReserva = new Reserva("77b11aa035", LocalDateTime.of(2024, 1, 1, 20, 0),
                2, "66c67aa035ed1f735450b7a2", "77b57d371994ae7f1b5e1od8", RESERVADO);
        var reservaList = List.of(primeiraReserva, segundaReserva);

        return new PagedModel<>(new PageImpl<>(reservaList));
    }

    static PagedModel<ListarReservasPorRestauranteIdOutput> getListarReservasPorRestauranteIdOutputPagedModel() {
        var primeiraReservaResponse = new ListarReservasPorRestauranteIdOutput("66c67aa035", "66e39d371994ae7f1b5e9ff0", "66c67aa035ed1f735450b7a2",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var segundaReservaResponse = new ListarReservasPorRestauranteIdOutput("77b11aa035", "77b57d371994ae7f1b5e1od8", "66c67aa035ed1f735450b7a2",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var listarReservasPorRestauranteIdOutputs = List.of(primeiraReservaResponse, segundaReservaResponse);

        return new PagedModel<>(new PageImpl<>(listarReservasPorRestauranteIdOutputs));
    }
}
