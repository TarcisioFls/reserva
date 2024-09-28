package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;

public interface AtualizarStatusReservaGateway {

    Reserva atualizarStatus(Reserva reserva);
}
