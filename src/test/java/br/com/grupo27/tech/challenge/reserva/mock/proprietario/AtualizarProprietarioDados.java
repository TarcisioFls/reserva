package br.com.grupo27.tech.challenge.reserva.mock.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.request.AtualizarProprietarioRequest;
import br.com.grupo27.tech.challenge.reserva.domain.input.proprietario.AtualizarProprietarioInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.proprietario.AtualizarProprietarioOutput;

public interface AtualizarProprietarioDados {

    static AtualizarProprietarioRequest getAtualizarProprietarioRequest() {
        return new AtualizarProprietarioRequest("Maria", "maria@teste.com", "abcd", "11988888888", "11999999999");
    }

    static AtualizarProprietarioInput getAtualizarProprietarioInput() {
        return new AtualizarProprietarioInput(null, "Maria", "maria@teste.com", "abcd", "11988888888", "11999999999");
    }

    static AtualizarProprietarioOutput getAtualizarProprietarioOutput() {
        return new AtualizarProprietarioOutput("77b11aa035ed1f735459a1p0", "Maria", "maria@teste.com", "abcd", "11988888888", "11999999999");
    }

}
