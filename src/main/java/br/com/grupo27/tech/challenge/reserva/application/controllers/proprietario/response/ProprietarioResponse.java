package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProprietarioResponse {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

}
