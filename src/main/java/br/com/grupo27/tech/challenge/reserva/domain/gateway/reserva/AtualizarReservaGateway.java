package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;

import java.util.Optional;

public interface AtualizarReservaGateway {

    Reserva atualizar(Reserva reserva);

    Optional<Reserva> buscarPorId(String id);
}
