package br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoOutput {

    private String id;
    private Integer nota;
    private String comentario;
    private String reservaId;


}
