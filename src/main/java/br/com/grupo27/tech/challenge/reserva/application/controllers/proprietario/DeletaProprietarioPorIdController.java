package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
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

    private final ProprietarioUserCaseFactory proprietarioUserCaseFactory;

    private final ProprietarioRepository proprietarioRepository;

    private final ProprietarioPresenter proprietarioPresenter;


    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePorId(@PathVariable String id) {

         var deletaProprietarioPorIdUserCase = proprietarioUserCaseFactory.buildDeletaProprietarioPorIdUserCase(
                 proprietarioPresenter, proprietarioRepository
         );

         deletaProprietarioPorIdUserCase.deletaPorId(id);

    }

}
