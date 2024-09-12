package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;

import java.time.LocalDate;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;

public interface CriarClienteDados {

    public static CriarClienteRequest getCriarClienteRequest(){
        return new CriarClienteRequest( "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }


    public static CriarClienteInput getCriarClienteInput(){
        return new CriarClienteInput(null, "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    public static CriarClienteInput getCriarClienteInputComId(){
        return new CriarClienteInput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    public static CriarClienteOutput getCriarClienteOutput(){
        return new CriarClienteOutput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

   public static CriarClienteRequest getCriarClienteRequestComNomeEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setNome("");
        return criarClienteRequest;

   }

    public static CriarClienteRequest getCriarClienteRequestComNomeNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setNome(null);
        return criarClienteRequest;

    }

    public static CriarClienteRequest getCriarClienteRequestComEmailBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setEmail("");
        return criarClienteRequest;

    }

    public static CriarClienteRequest getCriarClienteRequestComEmailNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setEmail(null);
        return criarClienteRequest;

    }

    public static CriarClienteRequest getCriarClienteRequestComSenhaEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setSenha("");
        return criarClienteRequest;

    }

    public static CriarClienteRequest getCriarClienteRequestComSenhaNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setSenha(null);
        return criarClienteRequest;

    }

    public static CriarClienteRequest getCriarClienteRequestComTelefoneEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setTelefone("");
        return criarClienteRequest;
    }

    public static CriarClienteRequest getCriarClienteRequestComTelefoneNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setTelefone(null);
        return criarClienteRequest;
    }

    public static CriarClienteRequest getCriarClienteRequestComCpfEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setCpf("");
        return criarClienteRequest;
    }

    public static CriarClienteRequest getCriarClienteRequestComCpfNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setCpf(null);
        return criarClienteRequest;
    }


    public static Cliente getClienteDepoisDeSalvar(){
        var cliente = getCliente();
        cliente.setId("66c67aa035ed1f735450b7a2");

        return cliente;
    }
}
