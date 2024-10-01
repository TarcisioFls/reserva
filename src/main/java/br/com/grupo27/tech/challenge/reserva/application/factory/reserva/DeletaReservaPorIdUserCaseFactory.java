package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.DeletaReservaPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.BuscarReservaPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva.DeletaReservaPorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaReservaPorIdUserCaseFactory {



        public DeletaReservaPorIdUserCase buildDeletaReservaPorIdUserCase(ReservaPresenter reservaPresenter,
                                                                          ReservaRepository reservaRepository){

            return new DeletaReservaPorIdUserCase(
                    buildDeletaReservaPorIdGateway(reservaRepository),
                    new BuscarReservaPorIdAdapter(reservaRepository, reservaPresenter)
            );
        }

        private DeletaReservaPorIdAdapter buildDeletaReservaPorIdGateway(ReservaRepository reservaRepository){

            return  new DeletaReservaPorIdAdapter(reservaRepository);
        }
}
