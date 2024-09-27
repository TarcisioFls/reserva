package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AtualizarReservaAdapter implements AtualizarReservaGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @Override
    public Reserva atualizar(Reserva reserva) {
        var reservaModel = reservaPresenter.reservaEmReservaModel(reserva);
        reservaModel = reservaRepository.save(reservaModel);

        return reservaPresenter.reservaModelEmReserva(reservaModel);
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {

       return reservaRepository.findById(id).map(reservaPresenter::reservaModelEmReserva);
    }
}
