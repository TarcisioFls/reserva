package br.com.grupo27.tech.challenge.reserva.application.controllers.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class PaginacaoResponse {

    private int total;

    private Pageable pageable;
}
