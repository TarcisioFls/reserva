package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CPF_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.NOME_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.SENHA_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.TELEFONE_OBRIGATORIO;
import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class Proprietario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    public Proprietario(String id, String nome, String email, String senha, String telefone, String cpf) {

        if (isNull(email) || email.isBlank()) {
            throw new ExceptionAdvice(EMAIL_OBRIGATORIO);
        }

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }

        if (isNull(senha) || senha.isBlank()) {
            throw new ExceptionAdvice(SENHA_OBRIGATORIO);
        }

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(TELEFONE_OBRIGATORIO);
        }

        if (isNull(cpf) || cpf.isBlank()) {
            throw new ExceptionAdvice(CPF_OBRIGATORIO);
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
    }
}
