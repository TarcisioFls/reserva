package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.AVALIACAO_INVALIDA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.AVALIACAO_OBRIGATORIA;
import static java.util.Objects.isNull;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Avaliacao {

    private String id;
    private Integer nota;
    private String comentario;
    private String reservaId;

    public Avaliacao(Integer nota, String comentario, String reservaId) {
        this.setNota(nota);
        this.setComentario(comentario);
        this.setReservaId(reservaId);
    }

    public Avaliacao(String id, Integer nota, String comentario, String reservaId) {
        this(nota, comentario, reservaId);
        this.setId(id);
    }

    public Avaliacao setId(String id) {
        this.id = id;
        return this;
    }

    public Avaliacao setNota(Integer nota) {
        if (isNull(nota)) {
            throw new ExceptionAdvice(AVALIACAO_OBRIGATORIA);
        } else if (nota < 0 || nota > 5) {
            throw new ExceptionAdvice(AVALIACAO_INVALIDA);
        }

        this.nota = nota;
        return this;
    }


    public Avaliacao setComentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public Avaliacao setReservaId(String reservaId) {
        this.reservaId = reservaId;
        return this;
    }
}
