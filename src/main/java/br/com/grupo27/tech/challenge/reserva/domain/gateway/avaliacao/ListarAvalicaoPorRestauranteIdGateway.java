package br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface ListarAvalicaoPorRestauranteIdGateway {

    Optional<List<Avaliacao>> listarPorRestaurante(String restauranteId);
}
