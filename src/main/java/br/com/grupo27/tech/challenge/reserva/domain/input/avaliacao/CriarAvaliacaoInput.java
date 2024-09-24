package br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarAvaliacaoInput {

    private Integer nota;
    private String comentario;
    private String reservaId;
}
