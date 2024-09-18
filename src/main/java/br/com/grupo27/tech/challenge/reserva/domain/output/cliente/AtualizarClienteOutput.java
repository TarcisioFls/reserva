package br.com.grupo27.tech.challenge.reserva.domain.output.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AtualizarClienteOutput extends ClienteOutput{

    public AtualizarClienteOutput(String id, String nome, String email, String senha, String telefone, String cpf, LocalDate dataNascimento) {
        super(id, nome, email, senha, telefone, cpf, dataNascimento);
    }
}
