package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantesGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantesOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class BuscarRestaurantesUserCase {

    private final BuscarRestaurantesGateway buscarRestaurantesGateway;

    private final BuscarRestaurantesPresenter buscarRestaurantesPresenter;

    public PagedModel<BuscarRestaurantesOutput> buscarRestaurantes(String buscar) {

        var restauranteList = buscarRestaurantesGateway.buscar(buscar);

        return buscarRestaurantesPresenter.restauranteEmBuscarRestauranteOutput(restauranteList);


    }
}
