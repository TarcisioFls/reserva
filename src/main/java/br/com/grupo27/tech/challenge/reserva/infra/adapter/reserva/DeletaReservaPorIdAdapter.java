package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaReservaPorIdAdapter implements DeletaReservaPorIdGateway {

    private final ReservaRepository reservaRepository;

    @Override
    public void deletaPorId(String id) {

        reservaRepository.deleteById(id);
    }
}
