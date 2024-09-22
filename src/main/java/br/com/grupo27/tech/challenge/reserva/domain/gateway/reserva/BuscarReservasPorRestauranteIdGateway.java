package br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BuscarReservasPorRestauranteIdGateway {

    Optional<List<Reserva>> buscarPorRestauranteIdEDataHora(String restauranteId, LocalDateTime horaData);
}
