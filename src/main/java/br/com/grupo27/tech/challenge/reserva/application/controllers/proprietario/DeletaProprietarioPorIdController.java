package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.DeletaProprietarioPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
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
@RequestMapping("/proprietarios")
public class DeletaProprietarioPorIdController {

    private final DeletaProprietarioPorIdUserCaseFactory deletaProprietarioPorIdUserCaseFactory;

    private final ProprietarioRepository proprietarioRepository;

    private final ProprietarioPresenter proprietarioPresenter;

    private final RestauranteRepository restauranteRepository;


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePorId(@PathVariable String id) {

         var deletaProprietarioPorIdUserCase = deletaProprietarioPorIdUserCaseFactory.buildDeletaProprietarioPorIdUserCase(
                 proprietarioPresenter, proprietarioRepository, restauranteRepository
         );

         deletaProprietarioPorIdUserCase.deletaPorId(id);

    }

}
