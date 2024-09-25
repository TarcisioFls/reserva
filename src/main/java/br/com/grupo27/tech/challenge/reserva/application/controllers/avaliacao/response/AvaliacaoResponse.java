package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoResponse {

    private String id;
    private Integer nota;
    private String comentario;
    private String reservaId;
}
