package br.com.grupo27.tech.challenge.reserva.domain.input.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarReservaInput {

    private String id;
    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String clienteId;
    private String restauranteId;

}
