package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorRestauranteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.ListarReservasPorRestauranteIdUserCase;
import org.springframework.stereotype.Component;

@Component
public class ListarReservasPorRestauranteIdUserCaseFactory {

    public ListarReservasPorRestauranteIdUserCase buildListarReservasPorRestauranteIdUserCase(
            ListarReservasPorRestauranteIdGateway listarReservasPorRestauranteIdGateway,
            ListarReservasPorRestauranteIdPresenter listarReservasPorRestauranteIdPresenter) {

        return new ListarReservasPorRestauranteIdUserCase(
                listarReservasPorRestauranteIdGateway,
                listarReservasPorRestauranteIdPresenter
        );
    }
}
