package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.cliente.BuscarClientePorIdDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;

import static org.junit.jupiter.api.Assertions.assertAll;

public class BuscarClientePorIdPresenterImplTeste  extends TesteConfig {

    @InjectMocks
    private BuscarClientePorIdPresenterImpl buscarClientePorIdPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeClienteEmBuscarClientePorIdOutput(){

        var buscarClientePorIdOutput = buscarClientePorIdPresenter.clienteEmBuscarClientePorIdOutput(getCliente());

        assertAll("buscarClientePorIdOutput",
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", buscarClientePorIdOutput.getId()),
                () -> Assertions.assertEquals("João Rodrigo", buscarClientePorIdOutput.getNome()),
                () -> Assertions.assertEquals("joao@teste.com", buscarClientePorIdOutput.getEmail()),
                () -> Assertions.assertEquals("123456", buscarClientePorIdOutput.getSenha()),
                () -> Assertions.assertEquals("11999999999", buscarClientePorIdOutput.getTelefone()),
                () -> Assertions.assertEquals("896.271.990-87", buscarClientePorIdOutput.getCpf()),
                () -> Assertions.assertEquals(LocalDate.of(1985,05,05), buscarClientePorIdOutput.getDataNascimento())
        );
    }

    @Test
    void testeClienteResponseEmBuscarClientePorIdOutput(){

        var buscarClientePorIdOutput = buscarClientePorIdPresenter.clienteResponseEmBuscarClientePorIdOutput(BuscarClientePorIdDados.buscarClientePorIdOutput());

        assertAll("buscarClienteResponse",
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", buscarClientePorIdOutput.getId()),
                () -> Assertions.assertEquals("João Rodrigo", buscarClientePorIdOutput.getNome()),
                () -> Assertions.assertEquals("joao@teste.com", buscarClientePorIdOutput.getEmail()),
                () -> Assertions.assertEquals("123456", buscarClientePorIdOutput.getSenha()),
                () -> Assertions.assertEquals("11999999999", buscarClientePorIdOutput.getTelefone()),
                () -> Assertions.assertEquals("896.271.990-87", buscarClientePorIdOutput.getCpf()),
                () -> Assertions.assertEquals(LocalDate.of(1985,05,05), buscarClientePorIdOutput.getDataNascimento())
        );




    }
}
