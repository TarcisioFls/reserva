package br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request;


import lombok.Data;


import java.time.LocalDate;
@Data

public class AtualizarClienteRequest {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;

    public AtualizarClienteRequest(String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }
}
