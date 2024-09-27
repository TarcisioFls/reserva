package br.com.grupo27.tech.challenge.reserva.domain.output.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CriarReservaOutput extends ReservaOutput {

    public CriarReservaOutput(String id, String clienteId, String restauranteId, int quantidadePessoas, LocalDateTime dataHora, ReservaStatus status) {
        super(id, dataHora, quantidadePessoas, restauranteId, clienteId, status);
    }
}
