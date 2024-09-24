package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class CriarClientePresenterImpl implements CriarClientePresenter {

    private final ModelMapper mapper;

    @Override
    public Cliente criarClienteInputEmCliente(CriarClienteInput criarClienteInput) {
        return new Cliente(criarClienteInput.getId(), criarClienteInput.getNome(),
                criarClienteInput.getEmail(), criarClienteInput.getSenha(),
                criarClienteInput.getTelefone(), criarClienteInput.getCpf(),
                criarClienteInput.getDataNascimento());
    }

    @Override
    public CriarClienteOutput clienteEmCriarClienteOutput(Cliente cliente) {
        return mapper.map(cliente, CriarClienteOutput.class);
    }

    @Override
    public CriarClienteInput criarClienteEmClienteInput(CriarClienteRequest request) {
        return mapper.map(request, CriarClienteInput.class);
    }

    @Override
    public ClienteResponse criarClienteOutputEmClienteResponse(CriarClienteOutput criarClienteOutput) {
        return mapper.map(criarClienteOutput, ClienteResponse.class);
    }
}
