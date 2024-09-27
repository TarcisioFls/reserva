package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.reserva.ListarReservasPorRestauranteIdInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.ListarReservasPorRestauranteIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorRestauranteIdPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarReservasPorRestauranteIdUserCase {

    private final ListarReservasPorRestauranteIdGateway listarReservasPorrestauranteIdGateway;
    private final ListarReservasPorRestauranteIdPresenter listarReservasPorRestauranteIdPresenter;

    public PagedModel<ListarReservasPorRestauranteIdOutput> buscarPorRestauranteId(ListarReservasPorRestauranteIdInput input) {

        var reservaPagedModel = listarReservasPorrestauranteIdGateway.listarPorRestauranteId(input);

        return listarReservasPorRestauranteIdPresenter.pageReservaModelEmPageReservaOutput(reservaPagedModel);
    }
}
