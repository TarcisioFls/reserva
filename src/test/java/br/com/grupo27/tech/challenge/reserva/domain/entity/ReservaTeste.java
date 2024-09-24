package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaTeste extends TesteConfig {

    @Test
    void reservaComDataHoraValida() {
        LocalDateTime dataHora = LocalDateTime.now().plusHours(3);
        var reserva = new Reserva().setDataHora(dataHora);
        assertEquals(dataHora, reserva.getDataHora());
    }

    @Test
    void reservaComQuantidadePessoasValida() {
        var reserva = new Reserva().setQuantidadePessoas(5);
        assertEquals(5, reserva.getQuantidadePessoas());
    }

    @Test
    void reservaComRestauranteIdValido() {
        var reserva = new Reserva().setRestauranteId("validRestauranteId");
        assertEquals("validRestauranteId", reserva.getRestauranteId());
    }

    @Test
    void reservaComClienteIdValido() {
        var reserva = new Reserva().setClienteId("validClienteId");
        assertEquals("validClienteId", reserva.getClienteId());
    }

    @Test
    void reservaComStatusValido() {
        var reserva = new Reserva().setStatus(RESERVADO);
        assertEquals(RESERVADO, reserva.getStatus());
    }

    @Test
    void reservaComDataHoraNula() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setDataHora(null)
        );
        assertEquals("Data e hora da reserva são obrigatórios", exceptionAdvice.getMessage());
    }

    @Test
    void reservaComQuantidadePessoasZero() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setQuantidadePessoas(0)
        );
        assertEquals("Quantidade de pessoas é obrigatória", exceptionAdvice.getMessage());
    }

    @Test
    void reservaComQuantidadePessoasNegativa() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setQuantidadePessoas(-1)
        );
        assertEquals("Quantidade de pessoas precisa ser maior que 0", exceptionAdvice.getMessage());
    }

    @Test
    void reservaComRestauranteIdNulo() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setRestauranteId(null)
        );
        assertEquals("Campo restauranteId é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void reservaComClienteIdNulo() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setClienteId(null)
        );
        assertEquals("Campo clienteId é obrigatório", exceptionAdvice.getMessage());
    }

    @Test
    void reservaComStatusNulo() {
        var exceptionAdvice = assertThrows(
                ExceptionAdvice.class, () -> new Reserva().setStatus(null)
        );
        assertEquals("Campo status é obrigatório", exceptionAdvice.getMessage());
    }

}