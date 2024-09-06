package br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;

import java.util.List;
import java.util.Optional;

public interface CriarRestauranteGateway {

    Restaurante criar(Restaurante restaurante);
}
