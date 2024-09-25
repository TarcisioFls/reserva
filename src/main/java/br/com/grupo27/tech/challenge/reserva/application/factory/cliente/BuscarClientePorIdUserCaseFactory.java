package br.com.grupo27.tech.challenge.reserva.application.factory.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.BuscarClientePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.BuscarClientePorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.BuscarClientePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BuscarClientePorIdUserCaseFactory {

    public BuscarClientePorIdUserCase buildBuscarClientePorIdUserCase(BuscarClientePorIdPresenter buscarClientePorIdPresenter,
                                                                      ClientePresenter clientePresenter,
                                                                      ClienteRepository clienteRepository) {

        return new BuscarClientePorIdUserCase(
                buildBuscarClientePorIdGateway(clientePresenter, clienteRepository),
                buscarClientePorIdPresenter
        );
    }

    public BuscarClientePorIdAdapter buildBuscarClientePorIdGateway(ClientePresenter clientePresenter,
                                                                     ClienteRepository clienteRepository) {

        return new BuscarClientePorIdAdapter(clienteRepository, clientePresenter);
    }
}
