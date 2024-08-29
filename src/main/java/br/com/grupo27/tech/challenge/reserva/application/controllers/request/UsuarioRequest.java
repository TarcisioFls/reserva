package br.com.grupo27.tech.challenge.reserva.application.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

}
