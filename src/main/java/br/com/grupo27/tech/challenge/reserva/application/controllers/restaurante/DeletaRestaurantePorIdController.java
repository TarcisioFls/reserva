package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.RestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.application.factory.restaurante.DeletaRestaurantePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantes")
public class DeletaRestaurantePorIdController {

    private final DeletaRestaurantePorIdUserCaseFactory deletaRestaurantePorIdUserCaseFactory;

    private final RestauranteRepository restauranteRepository;

    private final RestaurantePresenter restaurantePresenter;

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePorId(@PathVariable String id) {

         var deletaRestaurantePorIdUserCase = deletaRestaurantePorIdUserCaseFactory.buildDeletaRestaurantePorIdUserCase(
                 restauranteRepository
         );

         deletaRestaurantePorIdUserCase.deletaPorId(id);

    }

}
