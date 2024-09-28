package br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorClienteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorClienteIdOutput;
import org.springframework.data.web.PagedModel;

public interface ListarReservasPorClienteIdPresenter {

    ListarReservasPorClienteIdInput getListarReservasPorClienteIdInput(int pagina, int tamanho, String clienteId);

    PagedModel<ReservaResponse> listarReservasPorClienteIdOutputPageModelEmReservaResponsePageModel(PagedModel<ListarReservasPorClienteIdOutput> listarReservasPorClienteIdOutputPagedModel);

    PagedModel<ListarReservasPorClienteIdOutput> pageReservaModelEmPageReservaOutput(PagedModel<Reserva> reservaPagedModel);
}
