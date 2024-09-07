package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CPF_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.NOME_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.SENHA_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.TELEFONE_OBRIGATORIO;
import static java.util.Objects.isNull;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Proprietario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    public Proprietario(String id, String nome, String email, String senha, String telefone, String cpf) {

        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setTelefone(telefone);
        this.setCpf(cpf);
    }

    public Proprietario setId(String id) {

        this.id = id;
        return this;
    }

    public Proprietario setNome(String nome) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }

        this.nome = nome;
        return this;
    }

    public Proprietario setEmail(String email) {

        if (isNull(email) || email.isBlank()) {
            throw new ExceptionAdvice(EMAIL_OBRIGATORIO);
        }

        this.email = email;
        return this;
    }

    public Proprietario setSenha(String senha) {

        if (isNull(senha) || senha.isBlank()) {
            throw new ExceptionAdvice(SENHA_OBRIGATORIO);
        }

        this.senha = senha;
        return this;
    }

    public Proprietario setTelefone(String telefone) {

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(TELEFONE_OBRIGATORIO);
        }

        this.telefone = telefone;
        return this;
    }

    public Proprietario setCpf(String cpf) {

        if (isNull(cpf) || cpf.isBlank()) {
            throw new ExceptionAdvice(CPF_OBRIGATORIO);
        }

        this.cpf = cpf;
        return this;
    }
}
