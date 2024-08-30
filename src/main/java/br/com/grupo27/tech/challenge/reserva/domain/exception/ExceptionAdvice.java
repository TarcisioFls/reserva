package br.com.grupo27.tech.challenge.reserva.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ExceptionAdvice extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3326368034777406990L;

    private final CodigoError codigoError;

    public ExceptionAdvice(CodigoError codigoError) {
        super(codigoError.getMensagem());
        this.codigoError = codigoError;
    }

    public HttpStatus getHttpStatus() {
        return codigoError.getHttpStatus();
    }
}
