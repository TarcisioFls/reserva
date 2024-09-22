package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.BuscarClientePorIdOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarClientePorIdPresenterImpl implements BuscarClientePorIdPresenter{

    private final ModelMapper mapper;

    @Override
    public BuscarClientePorIdOutput clienteEmBuscarClientePorIdOutput(Cliente cliente) {
        return mapper.map(cliente, BuscarClientePorIdOutput.class);
    }

    @Override
    public ClienteResponse clienteResponseEmBuscarClientePorIdOutput(BuscarClientePorIdOutput buscarClientePorIdOutput) {
        return mapper.map(buscarClientePorIdOutput, ClienteResponse.class);
    }
}
