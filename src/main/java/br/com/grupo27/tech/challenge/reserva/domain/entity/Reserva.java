package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CAMPO_CLIENTE_ID_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CAMPO_RESTAURANTE_ID_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CAMPO_STATUS_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.DATA_HORA_OBRIGATORIA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.QUANTIDADE_PESSOAS_INVALIDA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.QUANTIDADE_PESSOAS_OBRIGATORIA;
import static java.util.Objects.isNull;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Reserva {

    private String id;
    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String restauranteId;
    private String clienteId;
    private ReservaStatus status;

    public Reserva (LocalDateTime dataHora, int quantidadePessoas, String restauranteId, String clienteId, ReservaStatus status) {

        this.setDataHora(dataHora);
        this.setQuantidadePessoas(quantidadePessoas);
        this.setRestauranteId(restauranteId);
        this.setClienteId(clienteId);
        this.setStatus(status);
    }

    public Reserva (String id, LocalDateTime dataHora, int quantidadePessoas, String restauranteId, String clienteId,
                    ReservaStatus status) {
        this(dataHora, quantidadePessoas, restauranteId, clienteId, status);

        this.setId(id);

    }

    public Reserva setId(String id) {
        this.id = id;
        return this;
    }

    public Reserva setDataHora(LocalDateTime dataHora) {

        if (dataHora == null) {
            throw new ExceptionAdvice(DATA_HORA_OBRIGATORIA);
        }

        this.dataHora = dataHora;
        return this;
    }

    public Reserva setQuantidadePessoas(int quantidadePessoas) {

        if (quantidadePessoas == 0) {
            throw new ExceptionAdvice(QUANTIDADE_PESSOAS_OBRIGATORIA);
        }

        if (quantidadePessoas < 0) {
            throw new ExceptionAdvice(QUANTIDADE_PESSOAS_INVALIDA);
        }

        this.quantidadePessoas = quantidadePessoas;
        return this;
    }

    public Reserva setRestauranteId(String restauranteId) {

        if (isNull(restauranteId) || restauranteId.isBlank()) {
            throw new ExceptionAdvice(CAMPO_RESTAURANTE_ID_OBRIGATORIO);
        }

        this.restauranteId = restauranteId;
        return this;
    }

    public Reserva setClienteId(String clienteId) {

        if (isNull(clienteId) || clienteId.isBlank()) {
            throw new ExceptionAdvice(CAMPO_CLIENTE_ID_OBRIGATORIO);
        }

        this.clienteId = clienteId;
        return this;
    }

    public Reserva setStatus(ReservaStatus status) {

        if (isNull(status)) {
            throw new ExceptionAdvice(CAMPO_STATUS_OBRIGATORIO);
        }

        this.status = status;
        return this;
    }
}
