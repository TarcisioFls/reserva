package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantePorIdGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;

@Slf4j
@RequiredArgsConstructor
public class DeletaRestaurantePorIdUserCase {

    private final DeletaRestaurantePorIdGateway deletaRestaurantePorIdGateway;
    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;
    private final DeletaReservasPorRestauranteIdGateway deletaReservasPorRestauranteIdGateway;

    public void deletaPorId(String id) {

        if (buscarRestaurantePorIdGateway.buscarPorId(id).isEmpty()) {
            throw new ExceptionAdvice(RESTAURANTE_NAO_ENCONTRADO);
        }

        deletaRestaurantePorIdGateway.deletaPorId(id);
        notificarCancelamentoReservasPorRestauranteId(id);
        deletaReservasPorRestauranteIdGateway.deletaPorRestauranteId(id);
    }

    private void notificarCancelamentoReservasPorRestauranteId(String restauranteId) {
        log.warn("Notificando cancelamento de reservas para o restaurante: " + restauranteId);
    }

}
