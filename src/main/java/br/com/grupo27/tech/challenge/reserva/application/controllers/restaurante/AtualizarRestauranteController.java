package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.AtualizarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.AtualizarRestauranteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class AtualizarRestauranteController {

    private final AtualizarRestauranteUserCaseFactory atualizarRestauranteUserCaseFactory;
    private final AtualizarRestaurantePresenter atualizarRestaurantePresenter;
    private final RestaurantePresenter restaurantePresenter;
    private final RestauranteRepository restauranteRepository;

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(@PathVariable String id, @RequestBody AtualizarRestauranteRequest request) {

        var atualizarRestauranteUserCase = atualizarRestauranteUserCaseFactory.buildAtualizarRestauranteUserCase(atualizarRestaurantePresenter, restaurantePresenter, restauranteRepository);
        var atualizarRestauranteInput = atualizarRestaurantePresenter.atualizarRestauranteRequestEmAtualizarRestauranteInput(id, request);
        var atualizarRestauranteOutput = atualizarRestauranteUserCase.atualizar(atualizarRestauranteInput);
        var restauranteResponse = atualizarRestaurantePresenter.atualizarRestauranteOutputEmRestauranteResponse(atualizarRestauranteOutput);

        return ResponseEntity.ok(restauranteResponse);
    }
}
