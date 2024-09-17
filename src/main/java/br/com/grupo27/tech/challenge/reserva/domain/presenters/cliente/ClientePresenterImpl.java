package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientePresenterImpl implements ClientePresenter{


    private final ModelMapper mapper;

    @Override
    public ClienteModel clienteEmClienteModel(Cliente cliente) {

        return mapper.map(cliente, ClienteModel.class);
    }

    @Override
    public Cliente clienteModelEmCliente(ClienteModel clienteModel) {

        return mapper.map(clienteModel, Cliente.class);
    }
}
