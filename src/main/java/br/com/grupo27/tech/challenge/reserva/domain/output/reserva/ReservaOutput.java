package br.com.grupo27.tech.challenge.reserva.domain.output.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class ReservaOutput {

    private String id;
    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String restauranteId;
    private String clienteId;
    private ReservaStatus status;
}
