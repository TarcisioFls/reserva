package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.grupo27.tech.challenge.reserva.mock.ClienteDados.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTeste extends TesteConfig {


    @Test
    void testaClienteComSucesso(){
        var cliente = getCliente();
        assertAll("teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", cliente.getId()),
                () -> assertEquals("João Rodrigo", cliente.getNome()),
                () -> assertEquals("joao@teste.com", cliente.getEmail()),
                () -> assertEquals("123456", cliente.getSenha()),
                () -> assertEquals("11999999999", cliente.getTelefone()),
                () -> assertEquals("896.271.990-87", cliente.getCpf()),
                () -> assertEquals(LocalDate.of(1985,05,05), cliente.getDataNascimento())
        );
    }

    @Test
    void testaClienteComNomeNull(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComNomeNull()
        );
        assertEquals("O campo nome deve ser preenchido com o nome e sobrenome", exceptionAdvice.getMessage());
    }
    @Test
    void testaClienteComNomeEmBranco(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComNomeEmBranco()
        );

        assertEquals("O campo nome deve ser preenchido com o nome e sobrenome", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComNomeSemSobrenome(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComNomeSemSobrenome()
        );

        assertEquals("O campo nome deve ser preenchido com o nome e sobrenome", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComEmailNull(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComEmailNull()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComEmailEmBranco(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComEmailEmBranco()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComSenhaNull(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComSenhaNull()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComSenhaEmBranco(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComSenhaEmBranco()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComTelefoneNull(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComTelefoneNull()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComTelefoneEmBranco(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComTelefoneEmBranco()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComCpfNull(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComCpfNull()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComCpfEmBranco(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComCpfEmBranco()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaClienteComCpfInvalido(){
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getClienteComCpfInvalido()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }
}
