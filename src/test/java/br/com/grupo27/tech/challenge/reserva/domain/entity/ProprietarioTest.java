package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.Test;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComCpfEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComCpfNull;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComEmailEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComEmailNull;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComNomeEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComNomeNull;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComSenhaEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComSenhadNull;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComTelefoneEmBranco;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioComTelefoneNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProprietarioTest extends TesteConfig {

    @Test
    void testeProprietarioComSucesso() {
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
    void testeProprietarioComNomeNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComNomeNull()
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComNomeEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComNomeEmBranco()
        );

        assertEquals("Nome é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComEmailNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComEmailNull()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComEmailEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComEmailEmBranco()
        );

        assertEquals("Email é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComPasswordNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComSenhadNull()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComPasswordEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComSenhaEmBranco()
        );

        assertEquals("Senha é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComTelefoneNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComTelefoneNull()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComTelefoneEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComTelefoneEmBranco()
        );

        assertEquals("Telefone é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComCpfNull() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComCpfNull()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void testeProprietarioComCpfEmBranco() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> getProprietarioComCpfEmBranco()
        );

        assertEquals("CPF é obrigatório", exceptionAdvice.getMessage());
    }

}