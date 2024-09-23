package br.com.grupo27.tech.challenge.reserva.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "avaliacoes")
public class AvaliacaoModel {

    @Id
    private String id;
    private Integer nota;
    private String comentario;
    private String reservaId;
}
