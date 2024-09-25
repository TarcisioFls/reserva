package br.com.grupo27.tech.challenge.reserva.mock.reserva;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Reserva;
import br.com.grupo27.tech.challenge.reserva.infra.model.ReservaModel;

import java.time.LocalDateTime;

import static br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus.RESERVADO;

public interface ReservaDados {

    static Reserva getReserva(){
        return new Reserva("66eb8772fd532626f457c740", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }

    static ReservaModel getReservaModel(){
        return new ReservaModel("66eb8772fd532626f457c740", LocalDateTime.of(2024,1,1,20,0), 2,
                "66c67aa035ed1f735450b7a2", "66e39d371994ae7f1b5e9ff0", RESERVADO);
    }
}
