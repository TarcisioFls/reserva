package br.com.grupo27.tech.challenge.reserva.mock;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ProprietarioDados {

    static ProprietarioResponse getProprietarioResponse() {
        return new ProprietarioResponse("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
    }

    static ProprietarioResponse getProprietarioResponseAtualizado() {
        return new ProprietarioResponse("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
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
        return new Proprietario("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
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
        return new ProprietarioModel("77b11aa035ed1f735459a1p0", "Maria", ",maria@teste.com", "abcd", "11988888888", "11999999999");
    }

    static Page<ProprietarioModel> getPageProprietarioModel() {

        var proprietarioModelJoao = new ProprietarioModel("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
        var proprietarioModelMaria = new ProprietarioModel("77b11aa035ed1f735459a1p0", "Maria", "maria@teste.com", "abcd", "11988888888", "11999999999");
        var proprietarioModelList = List.of(proprietarioModelJoao, proprietarioModelMaria);

        return new PageImpl<>(proprietarioModelList);
    }

    static PagedModel<Proprietario> getPageProprietario() {
        var proprietarioJoao = new Proprietario("66c67aa035ed1f735450b7a2", "João", "joao@teste.com", "123456", "11999999999", "11999999999");
        var proprietarioMaria = new Proprietario("77b11aa035ed1f735459a1p0", "Maria", "maria@teste.com", "abcd", "11988888888", "11999999999");
        var proprietarioList = List.of(proprietarioJoao, proprietarioMaria);
        var pageRequest = PageRequest.of(0, 10);
        var pageProprietario = new PageImpl<>(proprietarioList, pageRequest, proprietarioList.size());

         return new PagedModel<>(pageProprietario);
    }
}
