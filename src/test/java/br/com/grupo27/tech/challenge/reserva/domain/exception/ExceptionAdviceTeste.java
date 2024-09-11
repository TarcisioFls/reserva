package br.com.grupo27.tech.challenge.reserva.domain.exception;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;


import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.ERRO_DESCONHECIDO;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionAdviceTeste extends TesteConfig {

    @Test
    void testeExceptionAdvice() {
        var codigoError = ERRO_DESCONHECIDO;
        var exceptionAdvice = new ExceptionAdvice(codigoError);

        assertEquals(codigoError, exceptionAdvice.getCodigoError());
    }

    @Test
    void testeGetHttpStatus() {
        var codigoError = ERRO_DESCONHECIDO;
        var exceptionAdvice = new ExceptionAdvice(codigoError);

        assertEquals(codigoError.getHttpStatus(), exceptionAdvice.getHttpStatus());
    }

}