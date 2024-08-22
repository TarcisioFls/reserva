package br.com.grupo27.tech.challenge.reserva.infra.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "proprietarios")
public class ProprietarioModel {

    @Id
    private String id;
    private String nome;
    private String email;
    private String password;
    private String telefone;

}
