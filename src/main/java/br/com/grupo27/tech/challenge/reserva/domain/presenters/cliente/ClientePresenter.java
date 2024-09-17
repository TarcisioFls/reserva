package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;

public interface ClientePresenter {

    ClienteModel clienteEmClienteModel(Cliente cliente);
    Cliente clienteModelEmCliente(ClienteModel clienteModel);
}
