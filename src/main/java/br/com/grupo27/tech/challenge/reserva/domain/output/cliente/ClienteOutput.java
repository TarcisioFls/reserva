package br.com.grupo27.tech.challenge.reserva.domain.output.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOutput {

        private String id;
        private String nome;
        private String email;
        private String senha;
        private String telefone;
        private String cpf;
        private LocalDate dataNascimento;


}
