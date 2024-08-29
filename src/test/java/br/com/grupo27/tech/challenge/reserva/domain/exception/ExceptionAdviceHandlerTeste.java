package br.com.grupo27.tech.challenge.reserva.domain.exception;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExceptionAdviceHandlerTeste extends TesteConfig {

        @Test
        void testeHandleExceptionIsExibirDescricaoError() {
            var exceptionAdviceHandler = new ExceptionAdviceHandler();
            var exceptionAdvice = new ExceptionAdvice(CodigoError.ERRO_DESCONHECIDO);

            var response = exceptionAdviceHandler.handleException(exceptionAdvice);

            assertEquals(exceptionAdvice.getCodigoError().getHttpStatus(), response.getBody().getHttpStatus());
            assertEquals(exceptionAdvice.getMessage(), response.getBody().getMensagem());
            assertEquals(exceptionAdvice.getCodigoError().getHttpStatus().value(), response.getBody().getCodigo());
        }

    @Test
    void testeHandleExceptionNaoExibirDescricaoError() {
        var exceptionAdviceHandler = new ExceptionAdviceHandler();
        var exceptionAdvice = new ExceptionAdvice(CodigoError.EMAIL_JA_CADASTRADO);

        var response = exceptionAdviceHandler.handleException(exceptionAdvice);

        assertEquals(exceptionAdvice.getCodigoError().getHttpStatus(), response.getBody().getHttpStatus());
        assertEquals(exceptionAdvice.getMessage(), response.getBody().getMensagem());
        assertEquals(exceptionAdvice.getCodigoError().getHttpStatus().value(), response.getBody().getCodigo());
    }

    @Test
    void handleValidationExceptionsReturnsBadRequestWithErrors() {
        var exceptionAdviceHandler = new ExceptionAdviceHandler();
        var bindingResult = mock(BindingResult.class);
        var fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));
        var methodArgumentNotValidException = new MethodArgumentNotValidException(null, bindingResult);

        var response = exceptionAdviceHandler.handleValidationExceptions(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().getMensagem().contains("fieldName"));
        assertTrue(response.getBody().getMensagem().contains("defaultMessage"));
    }

    @Test
    void handleValidationExceptionsReturnsBadRequestWithEmptyErrors() {
        var exceptionAdviceHandler = new ExceptionAdviceHandler();
        var bindingResult = mock(BindingResult.class);
        when(bindingResult.getAllErrors()).thenReturn(Collections.emptyList());
        var methodArgumentNotValidException = new MethodArgumentNotValidException(null, bindingResult);

        var response = exceptionAdviceHandler.handleValidationExceptions(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{}", response.getBody().getMensagem());
    }

}