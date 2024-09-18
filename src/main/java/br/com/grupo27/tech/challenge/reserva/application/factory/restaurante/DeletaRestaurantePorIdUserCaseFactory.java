package br.com.grupo27.tech.challenge.reserva.application.factory.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante.DeletaRestaurantePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante.DeletaRestaurantePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DeletaRestaurantePorIdUserCaseFactory {

    public DeletaRestaurantePorIdUserCase buildDeletaRestaurantePorIdUserCase(
                                                                         RestauranteRepository restauranteRepository) {
        return new DeletaRestaurantePorIdUserCase(
                buildDeletaRestauranteGateway(restauranteRepository)
        );
    }

    private DeletaRestaurantePorIdAdapter buildDeletaRestauranteGateway(RestauranteRepository restauranteRepository) {
        return new DeletaRestaurantePorIdAdapter(restauranteRepository);
    }
}
