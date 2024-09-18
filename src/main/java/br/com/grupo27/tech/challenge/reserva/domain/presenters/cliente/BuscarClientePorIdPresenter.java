package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.BuscarClientePorIdOutput;

public interface BuscarClientePorIdPresenter {

    BuscarClientePorIdOutput clienteEmBuscarClientePorIdOutput(Cliente cliente);

    ClienteResponse clienteResponseEmBuscarClientePorIdOutput(BuscarClientePorIdOutput buscarClientePorIdOutput);
}
