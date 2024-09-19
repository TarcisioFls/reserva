package br.com.grupo27.tech.challenge.reserva.application.factory.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.CriarClienteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.CriarClienteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CriarClienteUserCaseFactory {

    public CriarClienteUserCase buidCriarClienteUserCase(CriarClientePresenter criarClientePresenter,
                                                         ClientePresenter clientePresenter,
                                                         ClienteRepository clienteRepository) {
        return new CriarClienteUserCase(
                buildCriarClienteGateway(clientePresenter, clienteRepository), criarClientePresenter);

    }


    private CriarClienteAdapter buildCriarClienteGateway(ClientePresenter clientePresenter, ClienteRepository clienteRepository) {
        return new CriarClienteAdapter(clienteRepository, clientePresenter);
    }
}
