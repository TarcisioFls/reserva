package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.CriarRestauranteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class CriarRestauranteController {

    private final CriarRestauranteUserCaseFactory criarRestauranteUserCaseFactory;
    private final CriarRestaurantePresenter criarRestaurantePresenter;
    private final RestaurantePresenter restaurantePresenter;
    private final RestauranteRepository restauranteRepository;


    @PostMapping
    public ResponseEntity<RestauranteResponse> criar(@RequestBody CriarRestauranteRequest request) {

        var criarRestauranteUserCase = criarRestauranteUserCaseFactory.buildCriarRestauranteUserCase(criarRestaurantePresenter, restaurantePresenter, restauranteRepository);
        var criarRestauranteInput = criarRestaurantePresenter.criarRestauranteParaCriarRestauranteInput(request);
        var criarRestauranteOutput = criarRestauranteUserCase.criar(criarRestauranteInput);
        var restauranteResponse = criarRestaurantePresenter.criarRestauranteOutputParaRestauranteResponse(criarRestauranteOutput);

        return ResponseEntity.ok(restauranteResponse);
    }
}
