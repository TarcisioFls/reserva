package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;

import java.util.Optional;

public interface BuscarReservaPorIdGateway {

    Optional<Reserva> buscarPorId(String id);

}
