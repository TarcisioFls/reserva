package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantePorIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantePorIdPresenter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class BuscarRestaurantePorIdUserCase {

    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;
    private final BuscarRestaurantePorIdPresenter buscarRestaurantePorIdPresenter;

    public BuscarRestaurantePorIdOutput buscarPorId(String id) {

        var restaurante = buscarRestaurantePorIdGateway.buscarPorId(id).orElseThrow(
                () -> new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO)
        );

        return buscarRestaurantePorIdPresenter.restauranteEmBuscarRestaurantePorIdOutput(restaurante);
    }
}
