package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.NOME_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PASSWORD_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.TELEFONE_OBRIGATORIO;
import static java.util.Objects.isNull;

public class Proprietario {

    private String id;
    private String nome;
    private String email;
    private String password;
    private String telefone;

    public Proprietario() {
    }

    public Proprietario(String id, String nome, String email, String password, String telefone) {

        if (isNull(email) || email.isBlank()) {
            throw new ExceptionAdvice(EMAIL_OBRIGATORIO);
        }

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }

        if (isNull(password) || password.isBlank()) {
            throw new ExceptionAdvice(PASSWORD_OBRIGATORIO);
        }

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(TELEFONE_OBRIGATORIO);
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public Proprietario setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Proprietario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Proprietario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Proprietario setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Proprietario setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }
}
