package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorClienteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorClienteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.ListarReservasPorClienteIdUserCase;
import org.springframework.stereotype.Component;

@Component
public class ListarReservasPorClienteIdUserCaseFactory {

    public ListarReservasPorClienteIdUserCase buildListarReservasPorClienteIdUserCase(
            ListarReservasPorClienteIdGateway listarReservasPorClienteIdGateway,
            ListarReservasPorClienteIdPresenter listarReservasPorClienteIdPresenter) {

        return new ListarReservasPorClienteIdUserCase (
                listarReservasPorClienteIdGateway,
                listarReservasPorClienteIdPresenter
        );
    }
}
