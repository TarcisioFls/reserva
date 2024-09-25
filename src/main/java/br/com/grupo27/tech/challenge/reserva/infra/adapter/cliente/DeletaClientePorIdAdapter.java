package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.DeletaClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaClientePorIdAdapter implements DeletaClientePorIdGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public void deletaPorId(String id) {

        clienteRepository.deleteById(id);
    }
}
