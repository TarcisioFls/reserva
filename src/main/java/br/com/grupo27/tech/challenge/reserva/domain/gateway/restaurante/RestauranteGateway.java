package br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante criar(Restaurante restaurante);

    Optional<Restaurante> buscar(String id);

    List<Restaurante> listar();

    Restaurante atualizar(String id, Restaurante restaurante);

    void excluir(String id);
}
