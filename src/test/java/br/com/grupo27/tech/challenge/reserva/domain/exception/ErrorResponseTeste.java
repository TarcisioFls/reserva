package br.com.grupo27.tech.challenge.reserva.domain.exception;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ErrorResponseTeste extends TesteConfig {

    @Test
    void testeErrorResponse() {
        var httpStatus = BAD_REQUEST;
        var mensagem = "Mensagem de erro";

        var erro = new ErrorResponse(httpStatus, mensagem);

        assertEquals(httpStatus, erro.getHttpStatus());
        assertEquals(mensagem, erro.getMensagem());
        assertEquals("Mensagem de erro", erro.getMensagem());
    }
}
