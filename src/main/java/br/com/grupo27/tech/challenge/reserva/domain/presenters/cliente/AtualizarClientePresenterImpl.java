package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.AtualizarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.AtualizarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.AtualizarClienteOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarClientePresenterImpl implements AtualizarClientePresenter {

    private final ModelMapper mapper;

    @Override
    public AtualizarClienteInput atualizarClienteRequestEmAtualizarClienteInput(String id, AtualizarClienteRequest atualizarClienteRequest) {
        var atualizarClienteInput = mapper.map(atualizarClienteRequest, AtualizarClienteInput.class);
        atualizarClienteInput.setId(id);
        return atualizarClienteInput;
    }

    @Override
    public Cliente atualizarClienteInputEmCliente(Cliente cliente, AtualizarClienteInput atualizarClienteInput) {
        return new Cliente(cliente.getId(),
                           atualizarClienteInput.getNome(),
                           atualizarClienteInput.getEmail(),
                           atualizarClienteInput.getSenha(),
                           atualizarClienteInput.getTelefone(),
                           atualizarClienteInput.getCpf(),
                           atualizarClienteInput.getDataNascimento());
    }

    @Override
    public AtualizarClienteOutput clienteEmAtualizarClienteOutput(Cliente cliente) {
        return mapper.map(cliente, AtualizarClienteOutput.class);
    }

    @Override
    public ClienteResponse atualizarClienteOutputEmClienteResponse(AtualizarClienteOutput atualizarClienteOutput) {
        return mapper.map(atualizarClienteOutput, ClienteResponse.class);
    }
}
