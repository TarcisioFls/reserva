package br.com.grupo27.tech.challenge.reserva.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Proprietario extends Usuario{

    public Proprietario(String id, String nome, String email, String senha, String telefone, String cpf) {

        super(id, nome, email, senha, telefone, cpf);
    }

}
