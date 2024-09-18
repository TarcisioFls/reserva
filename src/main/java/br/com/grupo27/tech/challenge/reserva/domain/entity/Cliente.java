package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;


import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.SENHA_OBRIGATORIO;
import static java.util.Objects.isNull;
@Data
public class Cliente {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;


    public Cliente() {};

    public Cliente(String id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {

        if ( Objects.isNull(email) || email.isBlank() ) {
            throw new ExceptionAdvice(CodigoError.EMAIL_OBRIGATORIO);
        }

        if ( Objects.isNull(telefone) || telefone.isBlank() ) {
            throw new ExceptionAdvice(CodigoError.TELEFONE_OBRIGATORIO);
        }
        if (isNull(senha) || senha.isBlank()) {
            throw new ExceptionAdvice(SENHA_OBRIGATORIO);
        }

        if (!validaCpf(cpf)){
            throw new ExceptionAdvice(CodigoError.CPF_OBRIGATORIO);
        }

        if (!validaNome(nome)){
            throw new ExceptionAdvice(CodigoError.NOME_CLIENTE_VALIDO);
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static boolean validaCpf(String cpf){

        if(Objects.isNull(cpf) || cpf.isEmpty()){
            return false;
        }
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

    public static boolean validaNome(String nome){
        if(Objects.isNull(nome) || nome.isEmpty()){
            return false;
        }

        return nome.matches("^\\p{L}+ \\p{L}+$");

    }
}
