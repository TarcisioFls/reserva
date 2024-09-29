package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservaPorIdGateway;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESERVA_NAO_ENCONTRADA;

@RequiredArgsConstructor
public class DeletaReservaPorIdUserCase {

    private final DeletaReservaPorIdGateway deletaReservaPorIdGateway;
    private final BuscarReservaPorIdGateway buscarReservaPorIdGateway;


    public void deletaPorId(String id) {

        if (buscarReservaPorIdGateway.buscarPorId(id).isEmpty()) {
            throw new ExceptionAdvice(RESERVA_NAO_ENCONTRADA);
        }

        deletaReservaPorIdGateway.deletaPorId(id);
    }


}
