package br.com.grupo27.tech.challenge.reserva.infra.adpter.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.CriarClienteGateway;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class CriarClienteAdapter implements CriarClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;

    @Override
    public Cliente criar(Cliente cliente) {
        var clienteModel = clientePresenter.clienteEmClienteModel(cliente);
        clienteRepository.save(clienteModel);
        cliente = clientePresenter.clienteModelEmCliente(clienteModel);
        return cliente;
    }

    @Override
    public Optional<Cliente> buscaPorEmail(String email) {
        return clienteRepository.findByEmail(email).map(clientePresenter::clienteModelEmCliente);
    }
}
