package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.ListarTodosClientesGateway;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.ListarTodosClientesOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ListarTodosClientesPresenter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Getter
@RequiredArgsConstructor
public class ListarTodosClientesUserCase {

    private final ListarTodosClientesGateway listarTodosClientesGateway;
    private final ListarTodosClientesPresenter listarTodosClientesPresenter;

    public PagedModel<ListarTodosClientesOutput> listarTodos(int pagina, int tamanho){

        var paginacaoCliente = listarTodosClientesGateway.listarTodos(PageRequest.of(pagina, tamanho));

        return listarTodosClientesPresenter.pageClienteEmPageTodosClienteOutput(paginacaoCliente);
    }
}
