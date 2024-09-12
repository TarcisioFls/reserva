package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.AtualizarClienteDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtualizarClientePresenterImpTeste extends TesteConfig {

    @InjectMocks
    private AtualizarClientePresenterImpl atualizarClientePresenter;


    @Spy
    private ModelMapper mapper;

    @Test
    void testeatualizarClienteRequestEmAtualizarClienteInput(){

        var id = "66c67aa035ed1f735450b7a2";
        var atualizarClienteRequest = getAtualizarClienteRequest();

        var resultado = atualizarClientePresenter.atualizarClienteRequestEmAtualizarClienteInput(id, atualizarClienteRequest);

        assertAll( "teste",
                () -> assertEquals(id, resultado.getId()),
                () -> assertEquals("João Rodrigo", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals("896.271.990-87", resultado.getCpf()),
                () -> assertEquals(LocalDate.of(1985,05,05), resultado.getDataNascimento())

        );


    }

    @Test
    void testeAtualizarClienteInputEmcliente(){
        var atualizarClienteInput = getAtualizarClienteInput();
        var cliente = getCliente();

        var resultado = atualizarClientePresenter.atualizarClienteInputEmCliente(cliente, atualizarClienteInput);

        assertAll( "teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João Rodrigo", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals("896.271.990-87", resultado.getCpf()),
                () -> assertEquals(LocalDate.of(1985,05,05), resultado.getDataNascimento())

        );
    }

    @Test
    void testeAtualizarClienteEmClienteOutput(){
        var cliente = getCliente();

        var resultado = atualizarClientePresenter.clienteEmAtualizarClienteOutput(cliente);

        assertAll( "teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João Rodrigo", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals("896.271.990-87", resultado.getCpf()),
                () -> assertEquals(LocalDate.of(1985,05,05), resultado.getDataNascimento())

        );
    }

    @Test
    void testeAtualizarClienteOutputEmClienteResponse(){
        var atualizarClienteOutput = getAtualizarClienteOutput();

        var resultado = atualizarClientePresenter.atualizarClienteOutputEmClienteResponse(atualizarClienteOutput);

        assertAll( "teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João Rodrigo", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals("896.271.990-87", resultado.getCpf()),
                () -> assertEquals(LocalDate.of(1985,05,05), resultado.getDataNascimento())

        );


    }
}
