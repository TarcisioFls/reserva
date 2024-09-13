package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response;

import br.com.grupo27.tech.challenge.reserva.application.controllers.response.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProprietarioResponse extends UsuarioResponse {

    private String id;

    public ProprietarioResponse(String id, String nome, String email, String senha, String telefone, String cpf) {
        super(nome, email, senha, telefone, cpf);
        this.id = id;
    }

}
