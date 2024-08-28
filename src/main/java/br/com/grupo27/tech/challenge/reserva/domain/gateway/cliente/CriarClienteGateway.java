package br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;


import java.util.Optional;

public interface CriarClienteGateway {

    Cliente criar(Cliente cliente);

    Optional<Cliente> buscaPorEmail(String email);

    Optional<Cliente> buscaPorCpf(String cpf);
}
