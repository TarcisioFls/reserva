package br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;

import java.util.Optional;

public interface AtualizarClienteGateway {

    Cliente atualizarCliente(Cliente cliente);

    Optional<Cliente> buscarPorId(String id);

    Optional<Cliente> buscarPorEmail(String email);

    Optional<Cliente> buscarPorCpf(String cpf);
}
