package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;


import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ListarTodosClientesPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ClienteUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.ListarTodosClientesUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ListarTodosClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteUserCaseFactory clienteUserCaseFactory;
    private final ClientePresenter clientePresenter;
    private final ListarTodosClientesPresenter listarTodosClientesPresenter;

    @GetMapping
    public ResponseEntity<PagedModel<ClienteResponse>> listarTodos(@RequestParam int pagina, @RequestParam(defaultValue = "50") int tamanho) {

        ListarTodosClientesUserCase listarTodosClientesUserCase = clienteUserCaseFactory.buildListarTodosClientesUserCase(
                listarTodosClientesPresenter, clientePresenter, clienteRepository);

        var todosclientesOutput = listarTodosClientesUserCase.listarTodos(pagina, tamanho);
        var clienteResponse = listarTodosClientesPresenter.pageTodosClienteOutputEmPageClienteListResponse(todosclientesOutput);

        return ResponseEntity.ok(clienteResponse);
    }

}
