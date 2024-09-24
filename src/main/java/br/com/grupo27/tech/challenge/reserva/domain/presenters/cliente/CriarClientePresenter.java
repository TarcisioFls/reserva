package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;


import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;

public interface CriarClientePresenter {

    Cliente criarClienteInputEmCliente(CriarClienteInput criarClienteInput);

    CriarClienteOutput clienteEmCriarClienteOutput(Cliente cliente);

    CriarClienteInput criarClienteEmClienteInput(CriarClienteRequest request);

    ClienteResponse criarClienteOutputEmClienteResponse(CriarClienteOutput criarClienteOutput);
}
