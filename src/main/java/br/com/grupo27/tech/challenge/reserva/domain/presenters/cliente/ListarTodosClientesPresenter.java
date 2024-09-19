package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.ListarTodosClientesOutput;
import org.springframework.data.web.PagedModel;

public interface ListarTodosClientesPresenter {

    PagedModel<ListarTodosClientesOutput> pageClienteEmPageTodosClienteOutput(PagedModel<Cliente> paginacaoCliente);

    PagedModel<ClienteResponse> pageTodosClienteOutputEmPageClienteListResponse(PagedModel<ListarTodosClientesOutput> todosClientesOutput);

}
