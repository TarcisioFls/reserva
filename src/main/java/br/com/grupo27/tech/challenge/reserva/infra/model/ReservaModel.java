package br.com.grupo27.tech.challenge.reserva.infra.model;

import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservas")
public class ReservaModel {

    @Id
    private String id;
    private LocalDateTime dataHora;
    private int quantidadePessoas;
    private String restauranteId;
    private String clienteId;
    private ReservaStatus status;

    public ReservaModel(LocalDateTime dataHora, int quantidadePessoas, String restauranteId, String clienteId, ReservaStatus status) {

        this.dataHora = dataHora;
        this.quantidadePessoas = quantidadePessoas;
        this.restauranteId = restauranteId;
        this.clienteId = clienteId;
        this.status = status;
    }

}
