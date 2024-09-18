package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.DeletaRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaRestaurantePorIdAdapter implements DeletaRestaurantePorIdGateway {

    private final RestauranteRepository restauranteRepository;

    @Override
    public void deletaPorId(String id) {

        restauranteRepository.deleteById(id);
    }
}
