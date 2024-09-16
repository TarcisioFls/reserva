package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaController {

    private final BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory;

    private final RestauranteRepository restauranteRepository;

    private final RestaurantePresenter restaurantePresenter;

    private final BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter;

    @GetMapping("/{buscar}")
    public ResponseEntity<PagedModel<RestauranteResponse>> buscarRestaurantes(@PathVariable String buscar) {

        var buscarRestauranteUserCase = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaUserCaseFactory
                .buildBuscarRestauranteUserCase(restauranteRepository, restaurantePresenter, buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter);
        var buscarRestaurantesOutputList = buscarRestauranteUserCase.buscarRestaurantes(buscar);
        var restauranteResponseList = buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenter
                .buscarRestaurantesOutputEmRestaurantesResponse(buscarRestaurantesOutputList);

        return ResponseEntity.ok(restauranteResponseList);
    }

}
