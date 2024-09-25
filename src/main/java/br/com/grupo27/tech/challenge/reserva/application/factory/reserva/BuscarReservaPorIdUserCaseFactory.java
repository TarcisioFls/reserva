package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.BuscarReservaPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.BuscarReservaPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservaPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarReservaPorIdUserCaseFactory {

    public BuscarReservaPorIdUserCase buildBuscarReservaPorIdUserCase(BuscarReservaPorIdPresenter buscarReservaPorIdPresenter,
                                                                      ReservaPresenter reservaPresenter,
                                                                      ReservaRepository reservaRepository) {
        return new BuscarReservaPorIdUserCase(
                buildReservaPorIdGateway(reservaPresenter, reservaRepository),
                buscarReservaPorIdPresenter
        );
    }

    private BuscarReservaPorIdAdapter buildReservaPorIdGateway(ReservaPresenter reservaPresenter, ReservaRepository reservaRepository) {
        return new BuscarReservaPorIdAdapter(reservaRepository, reservaPresenter);
    }
}
