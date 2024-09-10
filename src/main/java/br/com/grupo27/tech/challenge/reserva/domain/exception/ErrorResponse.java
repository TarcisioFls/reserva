package br.com.grupo27.tech.challenge.reserva.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String mensagem;
    private int codigo;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.mensagem = message;
        this.codigo = httpStatus.value();
    }

}
