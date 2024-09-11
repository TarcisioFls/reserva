package br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.CriarClienteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente.CriarClienteAdapter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class CriarClienteController {

    private final CriarClienteUserCase criarClienteUserCase;
    private final CriarClientePresenter criarClientePresenter;
    private final ClientePresenter clientePresenter;

   @Autowired
    public CriarClienteController(ClienteRepository clienteRepository,
                                     CriarClientePresenter criarClientePresenter,
                                     ClientePresenter clientePresenter) {
        this.criarClienteUserCase = new CriarClienteUserCase(new CriarClienteAdapter(clienteRepository, clientePresenter), criarClientePresenter);
        this.criarClientePresenter = criarClientePresenter;
        this.clientePresenter = clientePresenter;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody CriarClienteRequest criarClienteRequest) {

       var criarClienteInput = criarClientePresenter.criarClienteEmClienteInput(criarClienteRequest);
       var criarClienteOutput = criarClienteUserCase.criar(criarClienteInput);
       var clienteResponse = criarClientePresenter.criarClienteOutputEmClienteResponse(criarClienteOutput);

       return ResponseEntity.ok(clienteResponse);

    }
}
