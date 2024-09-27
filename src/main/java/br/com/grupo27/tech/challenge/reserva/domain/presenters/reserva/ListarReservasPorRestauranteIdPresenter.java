package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorRestauranteIdOutput;
import org.springframework.data.web.PagedModel;

public interface ListarReservasPorRestauranteIdPresenter {

    ListarReservasPorRestauranteIdInput getListarReservasPorRestauranteInput(int pagina, int tamanho, String restauranteId);

    PagedModel<ReservaResponse> listarReservasPorRestauranteIdOutputPageModelEmReservaResponsePageModel(PagedModel<ListarReservasPorRestauranteIdOutput> listarReservasPorRestauranteIdOutputPagedModel);

    PagedModel<ListarReservasPorRestauranteIdOutput> pageReservaModelEmPageReservaOutput(PagedModel<Reserva> reservaPagedModel);
}
