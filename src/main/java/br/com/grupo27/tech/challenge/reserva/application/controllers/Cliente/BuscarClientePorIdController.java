package br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.BuscarClientePorIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class BuscarClientePorIdController {

    private final ClienteUserCaseFactory clienteUserCaseFactory;
    private final BuscarClientePorIdPresenter buscarClientePorIdPresenter;
    private final ClienteRepository clienteRepository;
    private final ClientePresenter clientePresenter;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscaPorId(@PathVariable String id){

        var buscarClientePorIdUserCase = clienteUserCaseFactory.buildBuscarClientePorIdUserCase(buscarClientePorIdPresenter,
                clientePresenter, clienteRepository);

        var buscarClientePorIdOutPut = buscarClientePorIdUserCase.buscarPorId(id);
        var clienteResponse = buscarClientePorIdPresenter.clienteResponseEmBuscarClientePorIdOutput(buscarClientePorIdOutPut);

        return ResponseEntity.ok(clienteResponse);
    }
}