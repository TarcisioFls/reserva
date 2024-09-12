package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.request.AtualizarClienteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.input.cliente.AtualizarClienteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.AtualizarClienteOutput;

import java.time.LocalDate;

public interface AtualizarClienteDados {

    static AtualizarClienteRequest getAtualizarClienteRequest(){
        return new AtualizarClienteRequest("João atualizado", "joaoatualizado@teste.com","123456", "11999999999", "896.271.990-87", LocalDate.of(1985,05,05) );
    }

    static AtualizarClienteInput getAtualizarClienteInput(){
        return new AtualizarClienteInput(null, "joão atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }

    static AtualizarClienteInput getAtualizarClienteInputComId(){
        return new AtualizarClienteInput("66c67aa035ed1f735450b7a2", "joão atualizado", "joaoatualizado@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }


    static AtualizarClienteOutput getAtualizarClienteOutput(){
        return new AtualizarClienteOutput("66c67aa035ed1f735450b7a2", "joão atualizado", "joaoatualizado@teste.com","123456", "11999999999", "896.271.990-87", LocalDate.of(1985,05,05) );
    }
}
