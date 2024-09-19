package br.com.grupo27.tech.challenge.reserva.domain.useCase.Cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.BuscarClientePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.BuscarClientePorIdUserCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.BuscarClientePorIdDados.buscarClientePorIdOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BuscarClientePorIdUserCaseTeste extends TesteConfig {

    @InjectMocks
    private BuscarClientePorIdUserCase buscarClientePorIdUserCase;

    @Mock
    private BuscarClientePorIdGateway buscarClientePorIdGateway;

    @Mock
    private BuscarClientePorIdPresenter buscarClientePorIdPresenter;

    @Test
    void testaBuscarClientePorId(){

        var id = "66c67aa035ed1f735450b7a2";

        when(buscarClientePorIdGateway.buscarClientePorId(id)).thenReturn(Optional.of(getCliente()));
        when(buscarClientePorIdPresenter.clienteEmBuscarClientePorIdOutput(getCliente()))
                .thenReturn(buscarClientePorIdOutput());

        var resultado = buscarClientePorIdUserCase.buscarPorId(id);

        assertAll("Validando o retorno da busca por id",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João Rodrigo", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("896.271.990-87", resultado.getCpf()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals(LocalDate.of(1985,05,05), resultado.getDataNascimento())
        );

        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(id);
        verify(buscarClientePorIdPresenter, times(1)).clienteEmBuscarClientePorIdOutput(getCliente());
    }

    @Test
    void testaBuscarClientePorIdNaoEncontrado(){

        var id = "000323233";

        when(buscarClientePorIdGateway.buscarClientePorId(id)).thenReturn(Optional.empty());

        var exceptionAdvice = assertThrows(ExceptionAdvice.class, () -> buscarClientePorIdUserCase.buscarPorId(id));

        assertEquals("Cliente não encontrado", exceptionAdvice.getMessage());

        verify(buscarClientePorIdGateway, times(1)).buscarClientePorId(id);
        verify(buscarClientePorIdPresenter, never()).clienteEmBuscarClientePorIdOutput(any());
    }
}
