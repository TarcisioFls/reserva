package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.DeletaReservaPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class DeletaReservaPorIdController {

    private final DeletaReservaPorIdUserCaseFactory deletaReservaPorIdUserCaseFactory;

    private final ReservaRepository reservaRepository;

    private final ReservaPresenter reservaPresenter;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaPorId(@PathVariable String id){

        var deletaReservaPorIdUsesCase = deletaReservaPorIdUserCaseFactory.buildDeletaReservaPorIdUserCase(reservaPresenter,
                reservaRepository);

        deletaReservaPorIdUsesCase.deletaPorId(id);
    }
}
