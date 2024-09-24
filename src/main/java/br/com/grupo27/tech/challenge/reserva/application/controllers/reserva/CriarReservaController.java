package br.com.grupo27.tech.challenge.reserva.application.controllers.reserva;

import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.request.CriarReservaRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.reserva.CriarReservaUseCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.CriarReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class CriarReservaController {

    private final CriarReservaUseCaseFactory criarReservaUseCaseFactory;
    private final CriarReservaPresenter criarReservaPresenter;
    private final ReservaPresenter reservaPresenter;
    private final ReservaRepository reservaRepository;
    private final RestauranteRepository restauranteRepository;
    private final RestaurantePresenter restaurantePresenter;
    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;
    private final Clock clock;

    @PostMapping
    public ResponseEntity<ReservaResponse> criar (@RequestBody CriarReservaRequest request) {

        var criarReservaUserCase = criarReservaUseCaseFactory
                .buildCriarReservaUseCase(criarReservaPresenter, reservaPresenter, reservaRepository, restauranteRepository,
                        restaurantePresenter, clienteRepository, clientePresenter, clock);

        var criarReservaInput = criarReservaPresenter.criarReservaRequestEmCriarReservaInput(request);
        var criarReservaOutput = criarReservaUserCase.criar(criarReservaInput);
        var reservaResponse = criarReservaPresenter.criarReservaOutputEmReservaResponse(criarReservaOutput);

        return ResponseEntity.ok(reservaResponse);
    }

}
