package br.com.grupo27.tech.challenge.reserva.application.factory.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarTodasReservasGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarTodasReservasPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.ListarTodasReservasUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarTodasReservasUserCaseFactory {

    public ListarTodasReservasUserCase buildListarTodasReservasUserCase(ListarTodasReservasGateway listarTodasReservasGateway,
                                                                        ListarTodasReservasPresenter listarTodasReservasPresenter) {

        return new ListarTodasReservasUserCase(listarTodasReservasGateway, listarTodasReservasPresenter);
    }
}
