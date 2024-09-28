package br.com.grupo27.tech.challenge.reserva.domain.useCase.Cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.ListarTodosClientesGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ListarTodosClientesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ListarTodosClientesUserCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getPageCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ListarTodosClientesDados.getPageTodosClientesOutput;
import static org.mockito.Mockito.*;

public class ListarTodosClientesUserCaseTest extends TesteConfig {

    @InjectMocks
    private ListarTodosClientesUserCase listarTodosClientesUserCase;

    @Mock
    private ListarTodosClientesGateway listarTodosClientesGateway;

    @Mock
    private ListarTodosClientesPresenter listarTodosClientesPresenter;

    @Test
    void testListarTodos(){
        int pagina = 0;
        int tamanho = 10;

        when(listarTodosClientesGateway.listarTodos(PageRequest.of(pagina,tamanho))).thenReturn(getPageCliente());
        when(listarTodosClientesPresenter.pageClienteEmPageTodosClienteOutput(getPageCliente())).thenReturn(getPageTodosClientesOutput());


        var resultado = listarTodosClientesUserCase.listarTodos(pagina,tamanho);

        verify(listarTodosClientesGateway, times(1)).listarTodos(PageRequest.of(pagina,tamanho));
        verify(listarTodosClientesPresenter, times(1)).pageClienteEmPageTodosClienteOutput(getPageCliente());
    }
}
