package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservaPorIdGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeletaReservaPorIdUserCase {

    private final DeletaReservaPorIdGateway deletaReservaPorIdGateway;


    public void deletaPorId(String id) {


        deletaReservaPorIdGateway.deletaPorId(id);
    }


}
