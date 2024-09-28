package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;

import java.util.List;

public interface BuscarReservasPorClientIdGateway {

    List<Reserva> buscarPorClientIdEStatusReservadoEDataHora(Reserva reserva, ReservaStatus status);

}
