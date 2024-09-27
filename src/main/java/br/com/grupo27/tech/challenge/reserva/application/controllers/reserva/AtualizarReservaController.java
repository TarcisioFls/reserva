package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.AtualizarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.AtualizarReservaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.cliente.BuscarClientePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorClientIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.BuscarReservasPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.BuscarRestaurantePorIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.AtualizarReservaPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class AtualizarReservaController {

    private final AtualizarReservaUserCaseFactory atualizarReservaUserCaseFactory;
    private final AtualizarReservaPresenter atualizarReservaPresenter;
    private final Clock clock;
    private final BuscarClientePorIdGateway buscarClientePorIdGateway;
    private final BuscarRestaurantePorIdGateway buscarRestaurantePorIdGateway;
    private final BuscarReservasPorRestauranteIdGateway buscarReservasPorRestauranteIdGateway;
    private final BuscarReservasPorClientIdGateway buscarReservasPorClientIdGateway;

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponse> atualizarPorId(@PathVariable String id, @RequestBody AtualizarReservaRequest request) {

        var atualizarReservaUserCase = atualizarReservaUserCaseFactory.buildAtualizarReservaUserCase(
                atualizarReservaPresenter,
                clock,
                buscarClientePorIdGateway,
                buscarRestaurantePorIdGateway,
                buscarReservasPorRestauranteIdGateway,
                buscarReservasPorClientIdGateway);
        var atualizarReservaInput = atualizarReservaPresenter.atualizarReservaRequestEmAtualizarReservaInput(id, request);
        var atualizarReservaOutput = atualizarReservaUserCase.atualizar(atualizarReservaInput);
        var atualizarReservaResponse = atualizarReservaPresenter.atualizarReservaOutputEmReservaResponse(atualizarReservaOutput);

        return ResponseEntity.ok(atualizarReservaResponse);
    }

}
