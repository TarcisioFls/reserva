package br.com.grupo27.tech.challenge.reserva.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "proprietarios")
public class ProprietarioModel {

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;

}
