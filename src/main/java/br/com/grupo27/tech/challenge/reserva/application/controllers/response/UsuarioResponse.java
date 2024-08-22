package br.com.grupo27.tech.challenge.reserva.application.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private String nome;
    private String email;
    private String password;
    private String telefone;

}
