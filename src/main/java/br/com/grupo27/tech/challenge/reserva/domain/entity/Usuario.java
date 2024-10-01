package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CPF_INVALIDO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CPF_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.EMAIL_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.NOME_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.SENHA_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.TELEFONE_OBRIGATORIO;
import static java.util.Objects.isNull;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Setter
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

    public Usuario(String id, String nome, String email, String senha, String telefone, String cpf) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setTelefone(telefone);
        this.setCpf(cpf);
    }


    public void setNome(String nome) {

        if(Objects.isNull(nome) || nome.isEmpty()){
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }

        this.nome = nome;
    }

    public void setEmail(String email) {

        if ( Objects.isNull(email) || email.isBlank() ) {
            throw new ExceptionAdvice(EMAIL_OBRIGATORIO);
        }

        this.email = email;
    }

    public void setSenha(String senha) {

        if (isNull(senha) || senha.isBlank()) {
            throw new ExceptionAdvice(SENHA_OBRIGATORIO);
        }

        this.senha = senha;
    }

    public void setTelefone(String telefone) {

        if ( Objects.isNull(telefone) || telefone.isBlank() ) {
            throw new ExceptionAdvice(TELEFONE_OBRIGATORIO);
        }

        this.telefone = telefone;
    }

    public void setCpf(String cpf) {

        if(Objects.isNull(cpf) || cpf.isEmpty()){
            throw new ExceptionAdvice(CPF_OBRIGATORIO);
        }

        if (!validaCpf(cpf)){
            throw new ExceptionAdvice(CPF_INVALIDO);
        }

        this.cpf = cpf;
    }

    public static boolean validaCpf(String cpf){

        //remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D","");

        if(cpf.length() != 11){
            return false;
        }
        // Verifica se todos os dígitos são iguais
        if(cpf.matches("(\\d)\\1{10}")){
            return false;
        }

        // Calcula os dígitos verificadores
        char dig10, dig11;
        int sm, i, r, num, peso;


        // Calculo do 1º Digito Verificador
        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig10 = '0';
        } else {
            dig10 = (char) (r + 48);
        }

        // Calculo do 2º Digito Verificador
        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11)) {
            dig11 = '0';
        } else {
            dig11 = (char) (r + 48);
        }

        // Verifica se os dígitos calculados conferem com os dígitos informados
        return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));

    }
}
