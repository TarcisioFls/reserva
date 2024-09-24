package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.DeletaClientePorIdGateway;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CLIENTE_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class DeletaClientePorIdUserCase {

    private final DeletaClientePorIdGateway deletaClientePorIdGateway;
    private final BuscarClientePorIdGateway buscarClientePorIdGateway;

    public void deletaPorId(String id) {

        if(buscarClientePorIdGateway.buscarClientePorId(id).isEmpty()) {
            throw new ExceptionAdvice((CLIENTE_NAO_ENCONTRADO));
        }

        //TODO: Implementar regra para verificar se existe Reserva vinculada ao Cliente

        deletaClientePorIdGateway.deletaPorId(id);
    }
}
