package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.ListarTodasReservasUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarTodasReservasGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarTodasReservasPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ListarTodasReservasController {

    private final ListarTodasReservasUserCaseFactory listarTodasReservasUserCaseFactory;
    private final ListarTodasReservasGateway listarTodasReservasGateway;
    private final ListarTodasReservasPresenter listarTodasReservasPresenter;

    @GetMapping
    public ResponseEntity<PagedModel<ReservaResponse>> listarTodos(@RequestParam(defaultValue = "0") int pagina,
                                                                   @RequestParam(defaultValue = "50") int tamanho) {

        var listarTodasReservasUserCase = listarTodasReservasUserCaseFactory.buildListarTodasReservasUserCase(
                listarTodasReservasGateway, listarTodasReservasPresenter);
        var pageReservaOutput = listarTodasReservasUserCase.listarTodos(pagina, tamanho);
        var reservaResponsePagedModel = listarTodasReservasPresenter.pageTodasReservasOutputEmPageReservasResponse(pageReservaOutput);

        return ResponseEntity.ok(reservaResponsePagedModel);
    }

}
