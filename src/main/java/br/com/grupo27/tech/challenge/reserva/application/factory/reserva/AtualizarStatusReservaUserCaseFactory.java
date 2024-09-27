package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarStatusReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.AtualizarStatusReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.AtualizarStatusReservaAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservaPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarStatusReservaUserCaseFactory {

    public AtualizarStatusReservaUserCase buildAtualizarStatusReservaUserCase(ReservaRepository reservaRepository,
                                                                              ReservaPresenter reservaPresenter,
                                                                              AtualizarStatusReservaPresenter atualizarStatusReservaPresenter) {

        return new AtualizarStatusReservaUserCase(buildBuscarReservaPorIdGateway(reservaRepository, reservaPresenter),
                buildAtualizarStatusReservaGateway(reservaRepository, reservaPresenter),
                atualizarStatusReservaPresenter);
    }

    private static AtualizarStatusReservaAdapter buildAtualizarStatusReservaGateway(ReservaRepository reservaRepository, ReservaPresenter reservaPresenter) {
        return new AtualizarStatusReservaAdapter(reservaRepository, reservaPresenter);
    }

    private static BuscarReservaPorIdAdapter buildBuscarReservaPorIdGateway(ReservaRepository reservaRepository, ReservaPresenter reservaPresenter) {

        return new BuscarReservaPorIdAdapter(reservaRepository, reservaPresenter);
    }
}
