package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.AtualizarClienteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AtualizarClienteAdapter implements AtualizarClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        var clienteModel = clientePresenter.clienteEmClienteModel(cliente);
        clienteRepository.save(clienteModel);

        return clientePresenter.clienteModelEmCliente(clienteModel);
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepository.findById(id).map(clientePresenter::clienteModelEmCliente);
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email).map(clientePresenter::clienteModelEmCliente);
    }
}
