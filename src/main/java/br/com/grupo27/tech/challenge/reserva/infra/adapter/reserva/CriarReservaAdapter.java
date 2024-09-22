package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.CriarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CriarReservaAdapter implements CriarReservaGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public Reserva criar(Reserva reserva) {

        var reservaModel = reservaPresenter.reservaEmReservaModel(reserva);
        reservaModel = reservaRepository.save(reservaModel);

        return reservaPresenter.reservaModelEmReserva(reservaModel);
    }
}
