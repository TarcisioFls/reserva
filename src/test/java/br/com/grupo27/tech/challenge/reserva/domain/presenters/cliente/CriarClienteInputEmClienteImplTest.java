package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CriarClienteInputEmClienteImplTest extends TesteConfig {

    @InjectMocks
    private CriarClientePresenterImpl criarClientePresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeCriarClienteInputEmCliente(){
        var criarClienteInput = getCriarClienteInputComId();
        var clienteEsperado  = getCliente();

        var resultado = criarClientePresenter.criarClienteInputEmCliente(criarClienteInput);

        assertEquals(clienteEsperado, resultado);
    }

    @Test
    void testaClienteEmCriarClienteOutput(){
        var cliente = getClienteDepoisDeSalvar();
        var criarClienteOutputEsperado = getCriarClienteOutput();

        var resultado = criarClientePresenter.clienteEmCriarClienteOutput(cliente);

        assertEquals(criarClienteOutputEsperado, resultado);
    }

    @Test
    void testaCriarClienteEmCriarClienteInput(){
        var criarClienteRequest = getCriarClienteRequest();
        var criarClienteInputEsperado = getCriarClienteInput();

        var resultado = criarClientePresenter.criarClienteEmClienteInput(criarClienteRequest);

        assertEquals(criarClienteInputEsperado, resultado);
    }

    @Test
    void testaCriarClienteOutputEmClienteResponse(){
        var criarClienteOutput = getCriarClienteOutput();
        var clienteResponseEsperado = getClienteResponse();

        var resultado = criarClientePresenter.criarClienteOutputEmClienteResponse(criarClienteOutput);

        assertEquals(clienteResponseEsperado, resultado);
    }
}
