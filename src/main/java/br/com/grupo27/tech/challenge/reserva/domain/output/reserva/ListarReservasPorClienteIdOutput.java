package br.com.grupo27.tech.challenge.reserva.domain.output.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListarReservasPorClienteIdOutput {

    private String id;
    private String clienteId;
    private String restauranteId;
    private int quantidadePessoas;
    private LocalDateTime dataHora;
    private ReservaStatus status;

}
