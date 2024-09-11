package br.com.grupo27.tech.challenge.reserva.infra.adapter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.AtualizarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AtualizarRestauranteAdapter implements AtualizarRestauranteGateway {

    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;

    @Override
    public Restaurante atualizar(Restaurante restaurante) {
        var restauranteModel = restaurantePresenter.restauranteParaRestauranteModel(restaurante);
        restauranteRepository.save(restauranteModel);

        return restaurantePresenter.restauranteModelParaRestaurante(restauranteModel);
    }

    @Override
    public Optional<Restaurante> buscarPorId(String id) {

        return restauranteRepository.findById(id).map(restaurantePresenter::restauranteModelParaRestaurante);
    }
}
