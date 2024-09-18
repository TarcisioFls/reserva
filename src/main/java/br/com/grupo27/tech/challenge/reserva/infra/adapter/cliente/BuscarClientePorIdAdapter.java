package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BuscarClientePorIdAdapter implements BuscarClientePorIdGateway {

    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;
    @Override
    public Optional<Cliente> buscarClientePorId(String id) {

        return clienteRepository.findById(id).map(clientePresenter::clienteModelEmCliente);
    }
}
