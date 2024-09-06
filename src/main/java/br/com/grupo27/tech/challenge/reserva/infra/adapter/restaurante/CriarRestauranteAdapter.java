package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.CriarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CriarRestauranteAdapter implements CriarRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;

    @Override
    public Restaurante criar(Restaurante restaurante) {
        var restauranteModel = restaurantePresenter.restauranteParaRestauranteModel(restaurante);
        restauranteRepository.save(restauranteModel);
        restaurante = restaurantePresenter.restauranteModelParaRestaurante(restauranteModel);

        return restaurante;
    }
}
