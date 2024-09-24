package br.com.grupo27.tech.challenge.reserva.domain.input.reserva;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReservaInput {

    private String id;
    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String clienteId;
    private String restauranteId;
    private ReservaStatus status;

    public CriarReservaInput (LocalDateTime dataHora, int quantidadePessoas, String clienteId, String restauranteId) {
        this.setDataHora(dataHora);
        this.setQuantidadePessoas(quantidadePessoas);
        this.setClienteId(clienteId);
        this.setRestauranteId(restauranteId);
    }

}
