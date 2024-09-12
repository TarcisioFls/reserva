package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.ListarTodosClientesGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@RequiredArgsConstructor
public class ListarTodosClientesAdapter implements ListarTodosClientesGateway {

    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;


    @Override
    public PagedModel<Cliente> listarTodos(PageRequest pageRequest) {

        var pageClienteModelList = clienteRepository.findAll(pageRequest);

        return clientePresenter.pageClienteModelListEmPageClienteList(pageClienteModelList);
    }
}
