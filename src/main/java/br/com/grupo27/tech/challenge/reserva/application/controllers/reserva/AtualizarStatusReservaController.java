package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.AtualizarStatusReservaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.AtualizarStatusReservaGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservaPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarStatusReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva.AtualizarStatusReservaUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.model.enums.ReservaStatus;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class AtualizarStatusReservaController {

    private final AtualizarStatusReservaUserCaseFactory atualizarStatusReservaUserCaseFactory;
    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;
    private final AtualizarStatusReservaPresenter atualizarStatusReservaPresenter;

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<ReservaResponse> atualizarStatus (@PathVariable String id, @PathVariable ReservaStatus status) {
        var atualizarStatusReservaUserCase = atualizarStatusReservaUserCaseFactory
                .buildAtualizarStatusReservaUserCase(reservaRepository, reservaPresenter, atualizarStatusReservaPresenter);
        var atualizarReservaOutput = atualizarStatusReservaUserCase.atualizarStatus(id, status);
        var reservaResponse = atualizarStatusReservaPresenter.atualizarReservaOutputEmReservaResponse(atualizarReservaOutput);

        return ResponseEntity.ok(reservaResponse);
    }

}
