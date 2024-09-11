package br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;

import java.util.Optional;

public interface AtualizarRestauranteGateway {

    Restaurante atualizar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(String id);
}
