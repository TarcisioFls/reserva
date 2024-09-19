package br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.CriarClienteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.CriarClienteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class CriarClienteController {

    private final ClienteUserCaseFactory clienteUserCaseFactory;
    private final CriarClientePresenter criarClientePresenter;
    private final ClientePresenter clientePresenter;
    private final ClienteRepository clienteRepository;


    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody CriarClienteRequest criarClienteRequest) {

       var criarClienteUserCase = clienteUserCaseFactory.buidCriarClienteUserCase(criarClientePresenter, clientePresenter, clienteRepository);

       var criarClienteInput = criarClientePresenter.criarClienteEmClienteInput(criarClienteRequest);
       var criarClienteOutput = criarClienteUserCase.criar(criarClienteInput);
       var clienteResponse = criarClientePresenter.criarClienteOutputEmClienteResponse(criarClienteOutput);

       return ResponseEntity.ok(clienteResponse);

    }
}
