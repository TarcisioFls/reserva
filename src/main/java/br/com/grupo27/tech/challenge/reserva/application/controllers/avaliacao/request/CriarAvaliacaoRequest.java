package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarAvaliacaoRequest {

    private Integer nota;
    private String comentario;
    private String reservaId;
}
