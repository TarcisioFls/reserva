package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
public class CriarRestauranteController {

//    private final CriarRestauranteUserCase criarRestauranteUserCase;
//    private final CriarRestaurantePresenter criarRestaurantePresenter;
//    private final RestaurantePresenter restaurantePresenter;
//
//    @Autowired
//    public CriarRestauranteController(RestauranteRepository restauranteRepository, CriarRestaurantePresenter criarRestaurantePresenter,
//                                      RestaurantePresenter restaurantePresenter) {
//        this.criarRestauranteUserCase = new CriarRestauranteUserCase(new CriarRestauranteAdapter(restauranteRepository, restaurantePresenter), criarRestaurantePresenter);
//        this.criarRestaurantePresenter = criarRestaurantePresenter;
//        this.restaurantePresenter = restaurantePresenter;
//    }
}
