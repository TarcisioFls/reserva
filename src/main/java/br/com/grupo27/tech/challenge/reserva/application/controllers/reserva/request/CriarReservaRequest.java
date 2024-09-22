package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarReservaRequest {

    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String restauranteId;
    private String clienteId;

}
