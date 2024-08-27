package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.CriarClienteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_JA_CADASTRADO;

@Setter
@AllArgsConstructor
public class CriarClienteUserCase {

    private final CriarClienteGateway criarClienteGateway;
    private final CriarClientePresenter criarClientePresenter;

    public CriarClienteOutput criar(CriarClienteInput criarClienteInput){
        criarClienteGateway.buscaPorEmail(criarClienteInput.getEmail()).ifPresent(cliente->{throw new ExceptionAdvice(EMAIL_JA_CADASTRADO);
        });

        var cliente = criarClientePresenter.criarClienteInputEmCliente(criarClienteInput);
        cliente = criarClienteGateway.criar(cliente);

        return criarClientePresenter.clienteEmCriarClienteOutput(cliente);
    }
}
