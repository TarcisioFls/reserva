package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.ListarTodosClientesOutput;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.time.LocalDate;
import java.util.List;

public interface ListarTodosClientesDados {

    static PagedModel<ListarTodosClientesOutput> getPageTodosClientesOutput(){
       var clienteJoaoRodrigo = new ListarTodosClientesOutput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
       var clienteJoaoAtualizado = new ListarTodosClientesOutput("66c67aa035ed1f735450b7a1", "João atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
       var todosClientesOutputList = List.of(clienteJoaoRodrigo, clienteJoaoAtualizado);
       var pageRequest = PageRequest.of(0, 10);

       var pageTodosClientesOutput = new PageImpl<>(todosClientesOutputList, pageRequest, todosClientesOutputList.size());

       return new PagedModel<>(pageTodosClientesOutput);
    }

    static PagedModel<ClienteResponse> getPageClienteResponse(){
        var clienteJoaoRodrigo = new ClienteResponse("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteJoaoAtualizado = new ClienteResponse("66c67aa035ed1f735450b7a1", "João atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteResponseList = List.of(clienteJoaoRodrigo, clienteJoaoAtualizado);
        var pageRequest = PageRequest.of(0, 10);

        var pageClienteResponse = new PageImpl<>(clienteResponseList, pageRequest, clienteResponseList.size());

        return new PagedModel<>(pageClienteResponse);
    }

    static PagedModel<Cliente> getPageCliente(){

        var clienteJoaoRodrigo = new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteJoaoAtualizado = new Cliente("66c67aa035ed1f735450b7a1", "João atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var pageRequest = PageRequest.of(0, 10);
        var clientes = List.of(clienteJoaoRodrigo, clienteJoaoAtualizado);

        var pageClientes = new PageImpl<>(clientes, pageRequest, clientes.size());

        return new PagedModel<>(pageClientes);
    }
}
