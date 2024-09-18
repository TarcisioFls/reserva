package br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.AtualizarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.AtualizarClienteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteUserCaseFactory {

    public AtualizarClienteUserCase buidAtualizarClienteUserCase( AtualizarClientePresenter atualizarClientePresenter,
                                                                  ClientePresenter clientePresenter,
                                                                  ClienteRepository clienteRepository)
    {
        return  new AtualizarClienteUserCase(
                buildAtualizarClienteGateway(clientePresenter, clienteRepository), atualizarClientePresenter);

    }

    private AtualizarClienteAdapter buildAtualizarClienteGateway(ClientePresenter clientePresenter, ClienteRepository clienteRepository){
        return new AtualizarClienteAdapter(clienteRepository, clientePresenter);
    }
}
