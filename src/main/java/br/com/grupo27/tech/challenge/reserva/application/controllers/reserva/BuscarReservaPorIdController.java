package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.BuscarReservaPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.BuscarReservaPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class BuscarReservaPorIdController {

    private final BuscarReservaPorIdUserCaseFactory buscarReservaPorIdUserCaseFactory;
    private final BuscarReservaPorIdPresenter buscarReservaPorIdPresenter;
    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> buscarPorId(@PathVariable String id) {

        var buscarReservaPorIdUserCase = buscarReservaPorIdUserCaseFactory.buildBuscarReservaPorIdUserCase(buscarReservaPorIdPresenter, reservaPresenter, reservaRepository);
        var buscarReservaPorIdOutput = buscarReservaPorIdUserCase.buscarPorId(id);
        var reservaResponse = buscarReservaPorIdPresenter.buscarReservaPorIdOutputEmReservaResponse(buscarReservaPorIdOutput);

        return ResponseEntity.ok(reservaResponse);
    }
}
