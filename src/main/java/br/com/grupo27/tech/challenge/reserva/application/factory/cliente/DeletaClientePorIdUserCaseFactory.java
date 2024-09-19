package br.com.grupo27.tech.challenge.reserva.application.factory.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.DeletaClientePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.DeletaClientePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaClientePorIdUserCaseFactory {

    private final BuscarClientePorIdUserCaseFactory buscarClientePorIdUserCaseFactory;

    public DeletaClientePorIdUserCase buildDeletaClientePorIdUserCase(ClientePresenter clientePresenter,
                                                                      ClienteRepository clienteRepository) {
        return new DeletaClientePorIdUserCase(
                buildDeletaClientePorIdGateway(clienteRepository),
                buscarClientePorIdUserCaseFactory.buildBuscarClientePorIdGateway(clientePresenter, clienteRepository)
        );
    }

    public DeletaClientePorIdAdapter buildDeletaClientePorIdGateway(ClienteRepository clienteRepository) {
        return new DeletaClientePorIdAdapter(clienteRepository);
    }

}
