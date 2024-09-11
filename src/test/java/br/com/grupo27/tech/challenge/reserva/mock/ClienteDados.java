package br.com.grupo27.tech.challenge.reserva.mock;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;

import java.time.LocalDate;

public interface ClienteDados {

    static ClienteResponse getClienteResponse(){
        return new ClienteResponse("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
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






}
