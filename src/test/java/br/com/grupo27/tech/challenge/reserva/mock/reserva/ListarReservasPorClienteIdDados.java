package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorClienteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorClienteIdOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface ListarReservasPorClienteIdDados {

    static ListarReservasPorClienteIdInput getListarReservasPorClienteInput() {

        return new ListarReservasPorClienteIdInput(0, 10, "66c67aa035ed1f735450b7a2");
    }

    static Page<ReservaModel> getReservaModelPage() {

        var primeiraReservaModel = new ReservaModel("77ab8882fd752626g657c871", LocalDateTime.of(2024, 1, 1, 20, 0), 2,
                "99b3456d371344ab6f1y1e9pad", "66c67aa035ed1f735450b7a2", RESERVADO);
        var segundaRservaModel = new ReservaModel("88bc9993fd862727h758d982", LocalDateTime.of(2024, 1, 1, 20, 0), 2,
                "fb008a04-4040-4c70-80ce-693d58f8939f", "66c67aa035ed1f735450b7a2", RESERVADO);
        var reservaModelList = List.of(primeiraReservaModel, segundaRservaModel);

        return new PageImpl<>(reservaModelList);
    }

    static PagedModel<Reserva> getReservaPageList() {

        var primeiraReserva = new Reserva("77ab8882fd752626g657c871", LocalDateTime.of(2024, 1, 1, 20, 0), 2,
                "99b3456d371344ab6f1y1e9pad", "66c67aa035ed1f735450b7a2", RESERVADO);
        var segundaReserva = new Reserva("88bc9993fd862727h758d982", LocalDateTime.of(2024, 1, 1, 20, 0), 2,
                "fb008a04-4040-4c70-80ce-693d58f8939f", "66c67aa035ed1f735450b7a2", RESERVADO);
        var reservaList = List.of(primeiraReserva, segundaReserva);

        return new PagedModel<>(new PageImpl<>(reservaList));
    }

    static PagedModel<ListarReservasPorClienteIdOutput> getListarReservasPorClienteIdOutputPagedModel() {
        var primeiraReservaResponse = new ListarReservasPorClienteIdOutput("77ab8882fd752626g657c871", "66c67aa035ed1f735450b7a2", "99b3456d371344ab6f1y1e9pad",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var segundaReservaResponse = new ListarReservasPorClienteIdOutput("88bc9993fd862727h758d982", "66c67aa035ed1f735450b7a2", "fb008a04-4040-4c70-80ce-693d58f8939f",
                2, LocalDateTime.of(2024, 1, 1, 20, 0), RESERVADO);
        var listarReservasPorRestauranteIdOutputs = List.of(primeiraReservaResponse, segundaReservaResponse);

        return new PagedModel<>(new PageImpl<>(listarReservasPorRestauranteIdOutputs));
    }
}
