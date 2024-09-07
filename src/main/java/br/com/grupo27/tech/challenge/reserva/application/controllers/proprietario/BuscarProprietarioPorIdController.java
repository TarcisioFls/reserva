package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.BuscarProprietarioPorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.ProprietarioUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proprietario")
public class BuscarProprietarioPorIdController {

    private final ProprietarioUserCaseFactory proprietarioUserCaseFactory;

    private final BuscarProprietarioPorIdPresenter buscarProprietarioPorIdPresenter;

    private final ProprietarioRepository proprietarioRepository;

    private final ProprietarioPresenter proprietarioPresenter;

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioResponse> buscaPorId(@PathVariable String id) {

       var buscarProprietarioPorIdUserCase = proprietarioUserCaseFactory.buildBuscarProprietarioPorIdUserCase(buscarProprietarioPorIdPresenter, proprietarioPresenter, proprietarioRepository);

        var buscarProprietarioPorIdOutput = buscarProprietarioPorIdUserCase.buscarPorId(id);
        var proprietarioResponse = buscarProprietarioPorIdPresenter.proprietarioResponseEmBuscarProprietarioPorIdOutput(buscarProprietarioPorIdOutput);

        return ResponseEntity.ok(proprietarioResponse);
    }

}
