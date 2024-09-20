package br.com.grupo27.tech.challenge.reserva.application.factory.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.BuscarClientePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.DeletaClientePorIdAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DeletaClientePorIdUserCaseFactoryTest extends TesteConfig {

    @Mock
    private BuscarClientePorIdUserCaseFactory buscarClientePorIdUserCaseFactory;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClientePresenter clientePresenter;

    @Mock
    private DeletaClientePorIdAdapter deletaClientePorIdAdapter;

    @Mock
    private BuscarClientePorIdAdapter buscarClientePorIdAdapter;

    @InjectMocks
    private DeletaClientePorIdUserCaseFactory deletaClientePorIdUserCaseFactory;

    @Test
    void testeBuildDeletaClientePorIdUserCase() {

        when(buscarClientePorIdUserCaseFactory.buildBuscarClientePorIdGateway(clientePresenter, clienteRepository))
                .thenReturn(buscarClientePorIdAdapter);

        var resultado = deletaClientePorIdUserCaseFactory.buildDeletaClientePorIdUserCase(clientePresenter, clienteRepository);

        assertNotNull(resultado);
        verify(buscarClientePorIdUserCaseFactory, times(1)).buildBuscarClientePorIdGateway(clientePresenter, clienteRepository);
    }
}