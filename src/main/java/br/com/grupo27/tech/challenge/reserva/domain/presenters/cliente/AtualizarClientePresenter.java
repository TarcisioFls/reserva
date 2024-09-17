package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.AtualizarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.AtualizarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.AtualizarClienteOutput;

public interface AtualizarClientePresenter {

            AtualizarClienteInput atualizarClienteRequestEmAtualizarClienteInput(String id, AtualizarClienteRequest atualizarClienteRequest);

            Cliente atualizarClienteInputEmCliente(Cliente cliente, AtualizarClienteInput atualizarClienteInput);

            AtualizarClienteOutput clienteEmAtualizarClienteOutput(Cliente cliente);

            ClienteResponse atualizarClienteOutputEmClienteResponse(AtualizarClienteOutput atualizarClienteOutput);

}
