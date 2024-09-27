package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.ListarReservasPorRestauranteIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.ListarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ListarReservasPorRestauranteIdPresenter;
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
public class ListarReservasPorRestauranteIdController {

    private final ListarReservasPorRestauranteIdUserCaseFactory listarReservasPorRestauranteIdUserCaseFactory;
    private final ListarReservasPorRestauranteIdGateway listarReservasPorRestauranteIdGateway;
    private final ListarReservasPorRestauranteIdPresenter listarReservasPorRestauranteIdPresenter;

    @GetMapping("/restaurante")
    public ResponseEntity<PagedModel<ReservaResponse>> buscarPorRestauranteId(@RequestParam String restauranteId,
                                                                              @RequestParam(defaultValue = "0") int pagina,
                                                                              @RequestParam(defaultValue = "50") int tamanho) {

        var listarReservasPorRestauranteIdUserCase = listarReservasPorRestauranteIdUserCaseFactory
                .buildListarReservasPorRestauranteIdUserCase(
                        listarReservasPorRestauranteIdGateway,
                        listarReservasPorRestauranteIdPresenter
                );
        var listarReservasPorRestauranteIdInput = listarReservasPorRestauranteIdPresenter
                .getListarReservasPorRestauranteInput(pagina, tamanho, restauranteId);
        var listarReservasPorRestauranteIdOutputPagedModel = listarReservasPorRestauranteIdUserCase
                .buscarPorRestauranteId(listarReservasPorRestauranteIdInput);
        var reservaResponsePagedModel = listarReservasPorRestauranteIdPresenter.listarReservasPorRestauranteIdOutputPageModelEmReservaResponsePageModel(listarReservasPorRestauranteIdOutputPagedModel);

        return ResponseEntity.ok(reservaResponsePagedModel);
    }

}
