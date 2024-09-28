package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.ListarReservasPorClienteIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorClienteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorClienteIdPresenter;
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
public class ListarReservasPorClienteIdController {

    private final ListarReservasPorClienteIdUserCaseFactory listarReservasPorClienteIdUserCaseFactory;
    private final ListarReservasPorClienteIdGateway listarReservasPorClienteIdGateway;
    private final ListarReservasPorClienteIdPresenter listarReservasPorClienteIdPresenter;

    @GetMapping("/cliente")
    public ResponseEntity<PagedModel<ReservaResponse>> buscarPorClienteId(@RequestParam String clienteId,
                                                                          @RequestParam(defaultValue = "0") int pagina,
                                                                          @RequestParam(defaultValue = "50") int tamanho) {

        var listarReservasPorClienteIdUserCase = listarReservasPorClienteIdUserCaseFactory
                .buildListarReservasPorClienteIdUserCase(
                        listarReservasPorClienteIdGateway,
                        listarReservasPorClienteIdPresenter
                );
        var listarReservasPorClienteIdInput = listarReservasPorClienteIdPresenter
                .getListarReservasPorClienteIdInput(pagina, tamanho, clienteId);
        var listarReservasPorRestauranteIdOutputPagedModel = listarReservasPorClienteIdUserCase
                .buscarPorClienteId(listarReservasPorClienteIdInput);
        var reservaResponsePagedModel = listarReservasPorClienteIdPresenter.listarReservasPorClienteIdOutputPageModelEmReservaResponsePageModel(listarReservasPorRestauranteIdOutputPagedModel);

        return ResponseEntity.ok(reservaResponsePagedModel);
    }

}
