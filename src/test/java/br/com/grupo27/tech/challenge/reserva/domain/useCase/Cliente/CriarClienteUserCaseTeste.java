package br.com.grupo27.tech.challenge.reserva.domain.useCase.Cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.CriarClienteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.CriarClienteUserCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_JA_CADASTRADO;
import static br.com.grupo27.tech.challenge.reserva.mock.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarClienteDados.getCriarClienteInput;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarClienteDados.getCriarClienteOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CriarClienteUserCaseTeste extends TesteConfig {

    @InjectMocks
    private CriarClienteUserCase criarClienteUserCase;

    @Mock
    private CriarClienteGateway criarClienteGateway;

    @Mock
    private CriarClientePresenter criarClientePresenter;

    @Test
    void criarClienteComSucesso(){
        var criaClienteInput = getCriarClienteInput();
        var cliente = getCliente();
        var criarClienteOutput = getCriarClienteOutput();

        when(criarClienteGateway.buscaPorEmail(any(String.class))).thenReturn(Optional.empty());
        when(criarClientePresenter.criarClienteInputEmCliente(any(CriarClienteInput.class))).thenReturn(cliente);
        when(criarClienteGateway.criar(any(Cliente.class))).thenReturn(cliente);
        when(criarClientePresenter.clienteEmCriarClienteOutput(any(Cliente.class))).thenReturn(criarClienteOutput);

        var resultado = criarClienteUserCase.criar(criaClienteInput);

        assertEquals(criarClienteOutput, resultado);

        verify(criarClienteGateway, times(1)).buscaPorEmail(any(String.class));
        verify(criarClientePresenter, times(1)).criarClienteInputEmCliente(any(CriarClienteInput.class));
        verify(criarClienteGateway, times(1)).criar(any(Cliente.class));
        verify(criarClientePresenter, times(1)).clienteEmCriarClienteOutput(any(Cliente.class));
    }

    @Test
    void criarClienteComEmailJaCadastrado(){
        var criaClienteInput = getCriarClienteInput();
        var cliente = getCliente();

        when(criarClienteGateway.buscaPorEmail(criaClienteInput.getEmail())).thenReturn(Optional.of(cliente));

        var exception = assertThrows(ExceptionAdvice.class, ()-> criarClienteUserCase.criar(criaClienteInput));

        assertEquals(EMAIL_JA_CADASTRADO, exception.getCodigoError());

        verify(criarClienteGateway,times(1)).buscaPorEmail(any(String.class));
        verify(criarClientePresenter,times(0)).criarClienteInputEmCliente(any(CriarClienteInput.class));
        verify(criarClienteGateway, times(0)).criar(any(Cliente.class));
        verify(criarClientePresenter, times(0)).clienteEmCriarClienteOutput(any(Cliente.class));
    }
}
