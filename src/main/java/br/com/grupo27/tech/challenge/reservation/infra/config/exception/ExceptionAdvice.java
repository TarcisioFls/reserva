package br.com.grupo27.tech.challenge.reservation.infra.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ExceptionAdvice extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3326368034777406990L;

    private final ErrorCode errorCode;

    public ExceptionAdvice(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
