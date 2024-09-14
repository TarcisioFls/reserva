package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantePorIdGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletaRestaurantePorIdUserCase {

    private final DeletaRestaurantePorIdGateway deletaRestaurantePorIdGateway;
//    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;

    public void deletaPorId(String id) {

//        if (buscarRestaurantePorIdGateway.buscarPorId(id).isEmpty()) {
//            throw new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO);
//        }

        //TODO: Implementar regra para deletar as reservas vinculadas ao restaurante

        deletaRestaurantePorIdGateway.deletaPorId(id);
    }

}
