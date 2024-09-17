package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.time.LocalDate;
import java.util.List;

public interface ClienteDados {

    static ClienteResponse getClienteResponse(){
        return new ClienteResponse("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static ClienteResponse getClienteAtualizadoResponse(){
        return new ClienteResponse("66c67aa035ed1f735450b7a2", "João Rodrigo atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static ClienteModel getClienteModel(){
        return new ClienteModel("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static ClienteModel getClienteModelSemId(){
        return new ClienteModel(null, "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getCliente(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteAtualizado(){
        return new Cliente("66c67aa035ed1f735450b7a2", "joão atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComNomeNull(){
        return new Cliente("66c67aa035ed1f735450b7a2", null, "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComNomeEmBranco(){
        return new Cliente("66c67aa035ed1f735450b7a2", "", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComNomeSemSobrenome(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComEmailNull(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", null, "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComEmailEmBranco(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComSenhaNull(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", null, "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComSenhaEmBranco(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComTelefoneNull(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", null,"896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComTelefoneEmBranco(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComCpfNull(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999",null, LocalDate.of(1985,05,05));
    }

    static Cliente getClienteComCpfEmBranco(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","", LocalDate.of(1985,05,05));
    }
    static Cliente getClienteComCpfInvalido(){
        return new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.991-87", LocalDate.of(1985,05,05));
    }

    static ClienteModel getClienteModelAtualizado(){
        return new ClienteModel("66c67aa035ed1f735450b7a2", "joão atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static Page<ClienteModel> getPageClienteModel(){

        var clienteJoaoRodrigo = new ClienteModel("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteJoaoAtualizado = new ClienteModel("66c67aa035ed1f735450b7a1", "João atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteModelList = List.of(clienteJoaoRodrigo, clienteJoaoAtualizado);

        return new PageImpl<>(clienteModelList);
    }

    static PagedModel<Cliente> getPageCliente(){

        var clienteJoaoRodrigo = new Cliente("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteJoaoAtualizado = new Cliente("66c67aa035ed1f735450b7a1", "João atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
        var clienteList = List.of(clienteJoaoRodrigo, clienteJoaoAtualizado);
        var pageRequest = PageRequest.of(0, 10);

        var pageCliente = new PageImpl<>(clienteList, pageRequest, clienteList.size());

        return new PagedModel<>(pageCliente);
    }






}
