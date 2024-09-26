package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.request.CriarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.CriarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.CriarClienteOutput;

import java.time.LocalDate;
import java.util.UUID;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.util.CpfUtility.generateCpf;

public interface CriarClienteDados {

    static CriarClienteRequest getCriarClienteUnicoRequest() {
        var uniqueEmail = "client" + UUID.randomUUID() + "@example.com";
        var uniqueCpf = generateCpf();
        return new CriarClienteRequest( "João Rodrigo", uniqueEmail, "123456", "11999999999",uniqueCpf, LocalDate.of(1985,5,5));
    }

    static CriarClienteRequest getCriarClienteRequest(){
        return new CriarClienteRequest( "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,5,5));
    }

    static CriarClienteInput getCriarClienteInput(){
        return new CriarClienteInput(null, "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,5,5));
    }

    static CriarClienteInput getCriarClienteInputComId(){
        return new CriarClienteInput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,5,5));
    }

    static CriarClienteOutput getCriarClienteOutput(){
        return new CriarClienteOutput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,5,5));
    }

    static CriarClienteRequest getCriarClienteRequestComNomeEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setNome("");
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComNomeNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setNome(null);
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComEmailBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setEmail("");
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComEmailNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setEmail(null);
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComSenhaEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setSenha("");
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComSenhaNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setSenha(null);
        return criarClienteRequest;

    }

    static CriarClienteRequest getCriarClienteRequestComTelefoneEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setTelefone("");
        return criarClienteRequest;
    }

    static CriarClienteRequest getCriarClienteRequestComTelefoneNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setTelefone(null);
        return criarClienteRequest;
    }

    static CriarClienteRequest getCriarClienteRequestComCpfEmBranco(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setCpf("");
        return criarClienteRequest;
    }

    static CriarClienteRequest getCriarClienteRequestComCpfNull(){
        var criarClienteRequest = getCriarClienteRequest();
        criarClienteRequest.setCpf(null);
        return criarClienteRequest;
    }

    static Cliente getClienteDepoisDeSalvar(){
        var cliente = getCliente();
        cliente.setId("66c67aa035ed1f735450b7a2");

        return cliente;
    }
}
