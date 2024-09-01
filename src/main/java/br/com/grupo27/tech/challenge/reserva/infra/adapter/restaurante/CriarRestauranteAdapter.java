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

    @Override
    public Optional<Restaurante> buscar(String id) {
        return Optional.empty();
    }

    @Override
    public List<Restaurante> listar() {
        return List.of();
    }

    @Override
    public Restaurante atualizar(String id, Restaurante restaurante) {
        return null;
    }

    @Override
    public void excluir(String id) {

    }
}
