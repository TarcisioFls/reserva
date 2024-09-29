package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaReservasPorRestauranteIdAdapter implements DeletaReservasPorRestauranteIdGateway {

    private final ReservaRepository reservaRepository;

    @Override
    public void deletaPorRestauranteId(String restauranteId) {

        reservaRepository.deleteByRestauranteId(restauranteId);
    }
}
