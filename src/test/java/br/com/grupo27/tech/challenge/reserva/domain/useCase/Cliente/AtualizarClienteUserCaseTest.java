package br.com.grupo27.tech.challenge.reserva.domain.useCase.Cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.AtualizarClienteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.AtualizarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.AtualizarClienteUserCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.AtualizarClienteDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteAtualizado;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AtualizarClienteUserCaseTest extends TesteConfig {

    @InjectMocks
    private AtualizarClienteUserCase atualizarClienteUserCase;

    @Mock
    private AtualizarClienteGateway atualizarClienteGateway;

    @Mock
    private AtualizarClientePresenter atualizarClientePresenter;

    @Test
    void testeAtualizarClienteComSucesso(){

        var cliente = getCliente();
        var atualizarClienteInput = getAtualizarClienteInput();
        var clienteAtualizado = getClienteAtualizado();

        when(atualizarClienteGateway.buscarPorId(atualizarClienteInput.getId())).thenReturn(Optional.of(cliente));
        when(atualizarClienteGateway.buscarPorEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));
        when(atualizarClientePresenter.atualizarClienteInputEmCliente(cliente, atualizarClienteInput)).thenReturn(clienteAtualizado);
        when(atualizarClienteGateway.atualizarCliente(cliente)).thenReturn(clienteAtualizado);
        when(atualizarClientePresenter.clienteEmAtualizarClienteOutput(any(Cliente.class))).thenReturn(getAtualizarClienteOutput());

        atualizarClienteUserCase.atualizar(atualizarClienteInput);

        verify(atualizarClienteGateway, times(1)).buscarPorId(atualizarClienteInput.getId());
        verify(atualizarClienteGateway, times(1)).buscarPorEmail(anyString());
        verify(atualizarClientePresenter, times(1)).atualizarClienteInputEmCliente(cliente, atualizarClienteInput);
        verify(atualizarClienteGateway, times(1)).atualizarCliente(any(Cliente.class));
//        verify(atualizarClientePresenter, times(1)).clienteEmAtualizarClienteOutput(any(Cliente.class));
    }

    @Test
    void testeAtualizarClienteComClienteNaoEncotrado(){

        var atualizarClienteInput = getAtualizarClienteInput();

        when(atualizarClienteGateway.buscarPorId(atualizarClienteInput.getId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> atualizarClienteUserCase.atualizar(atualizarClienteInput));

//        verify(atualizarClienteGateway, times(1)).buscarPorId(atualizarClienteInput.getId());
 //       verify(atualizarClienteGateway, times(1)).buscarPorEmail(atualizarClienteInput.getEmail());
 //       verify(atualizarClientePresenter, never()).atualizarClienteInputEmCliente(any(Cliente.class), any(AtualizarClienteInput.class));
//        verify(atualizarClienteGateway, never()).atualizarCliente(any(Cliente.class));
//        verify(atualizarClientePresenter, never()).clienteEmAtualizarClienteOutput(any(Cliente.class));

    }

    @Test
    void testaAtualizarClienteComEmailJaCadastrado(){

        var atualizarClienteInput = getAtualizarClienteInput();
        var cliente = getCliente();

        when(atualizarClienteGateway.buscarPorId(atualizarClienteInput.getId())).thenReturn(Optional.of(cliente));
        when(atualizarClienteGateway.buscarPorEmail(anyString())).thenReturn(Optional.of(cliente));

        assertThrows(Exception.class, () -> atualizarClienteUserCase.atualizar(atualizarClienteInput));

        verify(atualizarClienteGateway, times(1)).buscarPorId(atualizarClienteInput.getId());
        verify(atualizarClienteGateway, times(1)).buscarPorEmail(anyString());
        verify(atualizarClientePresenter, never()).atualizarClienteInputEmCliente(cliente, atualizarClienteInput);
        verify(atualizarClienteGateway, never()).atualizarCliente(any(Cliente.class));
        verify(atualizarClientePresenter, never()).clienteEmAtualizarClienteOutput(any(Cliente.class));
    }
}
