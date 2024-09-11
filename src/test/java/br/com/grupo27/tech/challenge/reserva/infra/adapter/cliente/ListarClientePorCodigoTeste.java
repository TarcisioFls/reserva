package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenterImpl;
import br.com.grupo27.tech.challenge.reserva.infra.adpter.cliente.CriarClienteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ListarClientePorCodigoTeste extends TesteConfig {

    @InjectMocks
    private CriarClienteAdapter criarClienteAdapter;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClientePresenterImpl clientePresenter;

    @Test
    void TestaobterPorCodigo(){

    }
}
