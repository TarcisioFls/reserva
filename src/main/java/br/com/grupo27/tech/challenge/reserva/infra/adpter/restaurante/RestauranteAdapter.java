package br.com.grupo27.tech.challenge.reserva.infra.adpter.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.RestauranteGateway;

import java.util.List;
import java.util.Optional;

public class RestauranteAdapter implements RestauranteGateway {

    @Override
    public Restaurante criar(Restaurante restaurante) {
        return null;
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
