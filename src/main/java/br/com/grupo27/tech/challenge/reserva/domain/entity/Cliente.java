package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;


    public Cliente() {};

    public Cliente(Long id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {

        if ( Objects.isNull(email) || email.isBlank() ) {
            throw new ExceptionAdvice(CodigoError.EMAIL_OBRIGATORIO);
        }

        if ( Objects.isNull(telefone) || telefone.isBlank() ) {
            throw new ExceptionAdvice(CodigoError.TELEFONE_OBRIGATORIO);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        //remove caracteres não numéricos
        cpf = cpf.replace("[^0-9]","");

        if(cpf.length() != 11){
            return false;
        }
        // Verifica se todos os dígitos são iguais
        if(cpf.matches("(\\d)\\1{10}")){
            return false;
        }

        int sum = 0;
        for(int i=0; i< 9; i++ ){
            sum += (cpf.charAt(i)-'0')*(10 -i);
        }
        int primeiroDigito = 11 - (sum%11);
        if(primeiroDigito >= 10){
            primeiroDigito = 0;
        }

        for(int i=0; i< 10; i++ ){
            sum += (cpf.charAt(i)-'0')*(10 -i);
        }
        int segundoDigito = 11 - (sum%11);
        if(segundoDigito >= 10){
            segundoDigito = 0;
        }

        return (primeiroDigito == (cpf.charAt(9) - '0')) && (segundoDigito == (cpf.charAt(10) - '0'));


    }

    public static boolean validaNome(String nome){
        if(Objects.isNull(nome) || nome.isEmpty()){
            return false;
        }

        return nome.matches("^\\p{L}+\\p{L}+$");

    }
}
