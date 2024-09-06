package br.com.grupo27.tech.challenge.reserva.mock;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;

public interface ProprietarioDados {

    static ProprietarioResponse getProprietarioResponse() {
        return new ProprietarioResponse("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static ProprietarioResponse getProprietarioResponseAtualizado() {
        return new ProprietarioResponse("66c67aa035ed1f735450b7a2", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
    }

    static ProprietarioModel getProprietarioModel() {
        return new ProprietarioModel("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static ProprietarioModel getProprietarioModelSemId() {
        return new ProprietarioModel(null, "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietario() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioAtualizado() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
    }

    static Proprietario getProprietarioSemId() {
        return new Proprietario(null, "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComNomeNull() {
        return new Proprietario("66c67aa035ed1f735450b7a2", null, "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComNomeEmBranco() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComEmailNull() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", null, "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComEmailEmBranco() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "", "123456", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComSenhadNull() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", null, "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComSenhaEmBranco() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "", "11999999999", "11999999999");
    }

    static Proprietario getProprietarioComTelefoneNull() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", null, "11999999999");
    }

    static Proprietario getProprietarioComTelefoneEmBranco() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "", "11999999999");
    }

    static Proprietario getProprietarioComCpfNull() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", null);
    }

    static Proprietario getProprietarioComCpfEmBranco() {
        return new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "");
    }

    static ProprietarioModel getProprietarioModelAtualizado() {
        return new ProprietarioModel("66c67aa035ed1f735450b7a2", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
    }
}
