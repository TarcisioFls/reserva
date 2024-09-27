package br.com.grupo27.tech.challenge.reserva.domain.input.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListarReservasPorRestauranteIdInput {

    private int pagina;
    private int tamanho;
    private String restauranteId;

}
