package br.com.grupo27.tech.challenge.reserva.domain.input.proprietario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarProprietarioInput {

    private String id;
    private String nome;
    private String email;
    private String password;
    private String telefone;

}
