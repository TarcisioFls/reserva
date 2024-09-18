package br.com.grupo27.tech.challenge.reserva.mock.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.BuscarProprietarioPorIdOutput;

public interface BuscarProprietarioPorIdDados {

    static BuscarProprietarioPorIdOutput buscarProprietarioPorIdOutput() {

        return new BuscarProprietarioPorIdOutput("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

}
