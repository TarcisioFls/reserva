package br.com.grupo27.tech.challenge.reserva.domain.output.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BuscarReservaPorIdOutput extends ReservaOutput {

    public BuscarReservaPorIdOutput(String id, LocalDateTime dataHora, int quantidadePessoas, String restauranteId, String clienteId, ReservaStatus status) {
        super(id, dataHora, quantidadePessoas, restauranteId, clienteId, status);
    }
}
