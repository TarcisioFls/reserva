package br.com.grupo27.tech.challenge.reservation.infra.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public enum ErrorCode {

    EMAIL_IS_REQUIRED("Email é obrigatório", INTERNAL_SERVER_ERROR, FALSE),
    OWNER_ALREADY_EXISTS_WITH_EMAIL("Proprietário já cadastrado com o email", INTERNAL_SERVER_ERROR, FALSE),
    OWNER_ALREADY_EXISTS("Proprietário já cadastrado", INTERNAL_SERVER_ERROR, FALSE),
    UNKNOWN_ERROR("Erro desconhecido", INTERNAL_SERVER_ERROR, TRUE);

    private final String message;
    private final HttpStatus httpStatus;
    private final int code;
    private final boolean stackTrace;

    ErrorCode(String message, HttpStatus httpStatus, boolean stackTrace) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
        this.stackTrace = stackTrace;
    }

}
