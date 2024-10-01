package br.com.grupo27.tech.challenge.reserva.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Usuario {

    @Setter
    private LocalDate dataNascimento;

    public Cliente(String id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {

        super(id, nome, email, senha, telefone, cpf);
        this.setDataNascimento(dataNascimento);
    }




}
