package br.com.grupo27.tech.challenge.reserva.domain.exception;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

class ErrorResponseTeste extends TesteConfig {

    @Test
    void testeErrorResponse() {
        var httpStatus = BAD_REQUEST;
        var message = "Mensagem de erro";

        var erro = new ErrorResponse(httpStatus, message);

        assertEquals(httpStatus, erro.getHttpStatus());
        assertEquals(message, erro.getMensagem());
        assertEquals(400, erro.getCodigo());
    }

}