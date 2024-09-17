package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getPageCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getPageClienteModel;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListarTodosClientesAdapterTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosClientesAdapter listarTodosClientesAdapter;

    @Mock
    private ClienteRepository   clienteRepository;

    @Mock
    private ClientePresenter clientePresenter;

    @Test
    void testaListarTodos(){
        var paginacaaoRequest = PageRequest.of(0, 10);
        var pageClienteModel = getPageClienteModel();

        when(clienteRepository.findAll(paginacaaoRequest)).thenReturn(pageClienteModel);
        when(clientePresenter.pageClienteModelListEmPageClienteList(pageClienteModel)).thenReturn(getPageCliente());

        var clientePage = listarTodosClientesAdapter.listarTodos(paginacaaoRequest);

        assertNotNull(clientePage);

        assertAll("clientePage",
                () -> assertEquals(2, clientePage.getContent().size()),
                () -> assertEquals(0, requireNonNull(clientePage.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(clientePage.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(clientePage.getMetadata()).totalElements())
        );

        assertAll("clientePage.content",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(clientePage.getContent().get(0)).getId()),
                () -> assertEquals("João Rodrigo", requireNonNull(clientePage.getContent().get(0)).getNome()),
                () -> assertEquals("66c67aa035ed1f735450b7a1", requireNonNull(clientePage.getContent().get(1)).getId()),
                () -> assertEquals("João atualizado", requireNonNull(clientePage.getContent().get(1)).getNome())
        );


    }
}
