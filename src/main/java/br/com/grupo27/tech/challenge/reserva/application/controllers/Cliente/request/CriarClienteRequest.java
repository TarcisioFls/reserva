package br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CriarClienteRequest {


    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private LocalDate dataNascimento;
}
