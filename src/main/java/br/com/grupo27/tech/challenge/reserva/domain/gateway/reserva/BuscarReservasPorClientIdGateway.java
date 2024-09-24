package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;

import java.util.List;

public interface BuscarReservasPorClientIdGateway {

    List<Reserva> buscarPorClientIdEDataHora(Reserva reserva);

}
