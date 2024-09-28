package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.reserva.BuscarReservaPorIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.BuscarReservaPorIdPresenter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_NAO_ENCONTRADA;

@RequiredArgsConstructor
public class BuscarReservaPorIdUserCase {

    private final BuscarReservaPorIdGateway buscarReservaPorIdGateway;
    private final BuscarReservaPorIdPresenter buscarReservaPorIdPresenter;

    public BuscarReservaPorIdOutput buscarPorId(String id) {

        var reserva = buscarReservaPorIdGateway.buscarPorId(id).orElseThrow(
                () -> new ExceptionAdvice(RESERVA_NAO_ENCONTRADA)
        );

        return buscarReservaPorIdPresenter.reservaEmBuscarReservaPorIdOutput(reserva);
    }
}
