package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarStatusReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarStatusReservaAdapter implements AtualizarStatusReservaGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public Reserva atualizarStatus(Reserva reserva) {
        var reservaModel = reservaPresenter.reservaEmReservaModel(reserva);
        reservaModel = reservaRepository.save(reservaModel);

        return reservaPresenter.reservaModelEmReserva(reservaModel);
    }
}
