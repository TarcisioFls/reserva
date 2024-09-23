package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletaClientePorIdAdapterTest extends TesteConfig {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private DeletaClientePorIdAdapter deletaClientePorIdAdapter;

    @Test
    void testeDeletaPorIdComSucesso() {

        var id = "66c67aa035ed1f735450b7a2";

        deletaClientePorIdAdapter.deletaPorId(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }
}