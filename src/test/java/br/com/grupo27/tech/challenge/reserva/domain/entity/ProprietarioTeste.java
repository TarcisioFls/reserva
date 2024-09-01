package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.Test;

import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComCpfEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComCpfNull;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComEmailEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComEmailNull;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComNomeEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComNomeNull;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComSenhaEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComSenhadNull;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComTelefoneEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioComTelefoneNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProprietarioTeste extends TesteConfig {

    @Test
    void testaProprietarioComSucesso() {
        var proprietario = getProprietario();
        assertAll("teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", proprietario.getId()),
                () -> assertEquals("João", proprietario.getNome()),
                () -> assertEquals("joao@teste.com", proprietario.getEmail()),
                () -> assertEquals("123456", proprietario.getSenha()),
                () -> assertEquals("11999999999", proprietario.getTelefone()),
                () -> assertEquals("11999999999", proprietario.getCpf())
        );
    }

    @Test
    void testaProprietarioComNomeNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComNomeNull()
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComNomeEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComNomeEmBranco()
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComEmailNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComEmailNull()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComEmailEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComEmailEmBranco()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComPasswordNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComSenhadNull()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComPasswordEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComSenhaEmBranco()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComTelefoneNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComTelefoneNull()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComTelefoneEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComTelefoneEmBranco()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComCpfNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComCpfNull()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testaProprietarioComCpfEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComCpfEmBranco()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

}