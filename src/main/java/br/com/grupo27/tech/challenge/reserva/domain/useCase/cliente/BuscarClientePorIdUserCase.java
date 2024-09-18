package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.BuscarClientePorIdOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.BuscarClientePorIdPresenter;
import lombok.RequiredArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CLIENTE_NAO_ENCONTRADO;

@RequiredArgsConstructor
public class BuscarClientePorIdUserCase {

    private final BuscarClientePorIdGateway buscarClientePorIdGateway;
    private final BuscarClientePorIdPresenter buscarClientePorIdPresenter;

    public BuscarClientePorIdOutput buscarPorId(String id){

        var cliente = buscarClientePorIdGateway.buscarClientePorId(id).orElseThrow(
                () -> new ExceptionAdvice(CLIENTE_NAO_ENCONTRADO)
        );

        return buscarClientePorIdPresenter.clienteEmBuscarClientePorIdOutput(cliente);
    }
}
