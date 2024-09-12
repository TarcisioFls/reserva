package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

public interface ClientePresenter {

    ClienteModel clienteEmClienteModel(Cliente cliente);
    Cliente clienteModelEmCliente(ClienteModel clienteModel);
    PagedModel<Cliente> pageClienteModelListEmPageClienteList(Page<ClienteModel> pageClienteModelList);
}
