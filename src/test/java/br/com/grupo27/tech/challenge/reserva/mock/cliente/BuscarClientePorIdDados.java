package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.domain.output.cliente.BuscarClientePorIdOutput;

import java.time.LocalDate;

public interface BuscarClientePorIdDados {

    static BuscarClientePorIdOutput buscarClientePorIdOutput() {

        return new BuscarClientePorIdOutput("66c67aa035ed1f735450b7a2", "João Rodrigo", "joao@teste.com", "123456", "11999999999","896.271.990-87", LocalDate.of(1985,05,05));
    }
}
