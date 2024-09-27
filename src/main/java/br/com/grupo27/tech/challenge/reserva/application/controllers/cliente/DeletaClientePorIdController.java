package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.factory.cliente.DeletaClientePorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class DeletaClientePorIdController {

    private final DeletaClientePorIdUserCaseFactory deletaClientePorIdUserCaseFactory;

    private final ClienteRepository clienteRepository;

    private final ClientePresenter clientePresenter;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaPorId(@PathVariable String id) {

        var deletaClientePorIdUserCase = deletaClientePorIdUserCaseFactory.buildDeletaClientePorIdUserCase(
                clientePresenter, clienteRepository
        );

        deletaClientePorIdUserCase.deletaPorId(id);
    }
}
