package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.AtualizarClienteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.AtualizarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.AtualizarClienteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.AtualizarClientePresenter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public class AtualizarClienteUserCase {

    private final AtualizarClienteGateway atualizarClienteGateway;
    private final AtualizarClientePresenter atualizarClientePresenter;

    public AtualizarClienteOutput atualizar(AtualizarClienteInput atualizarClienteInput){

        var cliente = atualizarClienteGateway.buscarPorId(atualizarClienteInput.getId()).orElseThrow(
                () -> new ExceptionAdvice(CodigoError.CLIENTE_NAO_ENCONTRADO));

        atualizarClienteGateway.buscarPorEmail(atualizarClienteInput.getEmail()).ifPresent(result ->{
            if(!result.getId().equals(atualizarClienteInput.getId())){
                throw  new ExceptionAdvice(CodigoError.EMAIL_JA_CADASTRADO);
            }
        });

        cliente = atualizarClientePresenter.atualizarClienteInputEmCliente(cliente, atualizarClienteInput);
        cliente = atualizarClienteGateway.atualizarCliente(cliente);

        return atualizarClientePresenter.clienteEmAtualizarClienteOutput(cliente);
    }
}
