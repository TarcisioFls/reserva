package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.ListarTodosClientesOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class ListarTodosClientesPresenterImpl implements ListarTodosClientesPresenter{

    private final ModelMapper mapper;
    @Override
    public PagedModel<ListarTodosClientesOutput> pageClienteEmPageTodosClienteOutput(PagedModel<Cliente> paginacaoCliente) {

        var listarTodosClientesOutputList = paginacaoCliente.getContent().stream()
                .map(cliente -> mapper.map(cliente, ListarTodosClientesOutput.class))
                .toList();

        var metadata = paginacaoCliente.getMetadata();
        var pageListarTodosClientesOutput = new PageImpl<>(listarTodosClientesOutputList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());

        return new PagedModel<>(pageListarTodosClientesOutput);
    }

    @Override
    public PagedModel<ClienteResponse> pageTodosClienteOutputEmPageClienteListResponse(PagedModel<ListarTodosClientesOutput> todosClientesOutput) {
        var clienteResponseList = todosClientesOutput.getContent().stream()
                .map(clienteOutput -> mapper.map(clienteOutput, ClienteResponse.class))
                .toList();

        var metadata = todosClientesOutput.getMetadata();
        var pageClienteResponse = new PageImpl<>(clienteResponseList, PageRequest.of((int) requireNonNull(metadata).number(), (int) metadata.size()), metadata.totalElements());
        return new PagedModel<>(pageClienteResponse);
    }
}
