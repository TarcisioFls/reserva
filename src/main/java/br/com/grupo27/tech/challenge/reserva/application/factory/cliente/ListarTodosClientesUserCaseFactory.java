package br.com.grupo27.tech.challenge.reserva.application.factory.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ListarTodosClientesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ListarTodosClientesUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.ListarTodosClientesAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ListarTodosClientesUserCaseFactory {

    public ListarTodosClientesUserCase buildListarTodosClientesUserCase(ListarTodosClientesPresenter listarTodosClientesPresenter,
                                                                        ClientePresenter clientePresenter,
                                                                        ClienteRepository clienteRepository) {
        return new ListarTodosClientesUserCase(buildListarTodosClientesGateway(clientePresenter, clienteRepository), listarTodosClientesPresenter);
    }

    private ListarTodosClientesAdapter buildListarTodosClientesGateway(ClientePresenter clientePresenter,
                                                                       ClienteRepository clienteRepository
    ) {
        return new ListarTodosClientesAdapter(clienteRepository, clientePresenter);
    }
}
