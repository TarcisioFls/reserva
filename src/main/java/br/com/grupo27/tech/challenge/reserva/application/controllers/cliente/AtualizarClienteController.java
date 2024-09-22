package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.request.AtualizarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.AtualizarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class AtualizarClienteController {

    private final ClienteUserCaseFactory clienteUserCaseFactory;
    private final AtualizarClientePresenter atualizarClientePresenter;
    private final ClientePresenter clientePresenter;
    private final ClienteRepository clienteRepository;

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable String id, @RequestBody AtualizarClienteRequest atualizarClienteRequest){

        var atualizarClienteUserCase = clienteUserCaseFactory.buidAtualizarClienteUserCase(atualizarClientePresenter, clientePresenter, clienteRepository);
        var atualizarClienteInput = atualizarClientePresenter.atualizarClienteRequestEmAtualizarClienteInput(id, atualizarClienteRequest);
        var atualizarClienteOutput = atualizarClienteUserCase.atualizar(atualizarClienteInput);
        var clienteResponse = atualizarClientePresenter.atualizarClienteOutputEmClienteResponse(atualizarClienteOutput);

        return ResponseEntity.ok(clienteResponse);
    }

}
