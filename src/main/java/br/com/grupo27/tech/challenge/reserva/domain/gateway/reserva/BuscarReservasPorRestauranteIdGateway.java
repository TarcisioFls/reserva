package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BuscarReservasPorRestauranteIdGateway {

    Optional<List<Reserva>> buscarPorRestauranteIdEStatusReservadoEDataHora(String restauranteId, ReservaStatus status, LocalDateTime horaData);
}
