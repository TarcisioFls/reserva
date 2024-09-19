package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BuscarClientePorIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private BuscarClientePorIdAdapter buscarClientePorIdAdapter;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClientePresenter clientePresenter;

    @Test
    void buscarClientePorId() {

        var id = "66c67aa035ed1f735450b7a2";

        when(clienteRepository.findById(id)).thenReturn(Optional.of(getClienteModel()));
        when(clientePresenter.clienteModelEmCliente(getClienteModel())).thenReturn(getCliente());

        var result = buscarClientePorIdAdapter.buscarClientePorId(id);

        assertTrue(result.isPresent());
        assertEquals(getCliente(), result.get());
    }
}
