package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorClienteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorClienteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorClienteIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorClienteIdPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarReservasPorClienteIdUserCase {

    private final ListarReservasPorClienteIdGateway listarReservasPorClienteIdGateway;
    private final ListarReservasPorClienteIdPresenter listarReservasPorClienteIdPresenter;

    public PagedModel<ListarReservasPorClienteIdOutput> buscarPorClienteId(ListarReservasPorClienteIdInput input) {

        var reservaPagedModel = listarReservasPorClienteIdGateway.listarPorClienteId(input);

        return listarReservasPorClienteIdPresenter.pageReservaModelEmPageReservaOutput(reservaPagedModel);
    }
}
