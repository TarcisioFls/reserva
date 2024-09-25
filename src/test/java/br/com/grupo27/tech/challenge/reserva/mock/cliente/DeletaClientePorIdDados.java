package br.com.grupo27.tech.challenge.reserva.mock.cliente;

import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;

import java.time.LocalDate;

public interface DeletaClientePorIdDados {

    String ID_DELETA_TESTE = "66c67aa035ed1f735450b7a2";

    static ClienteModel getClienteModel() {
        return criaClienteModel();
    }

    private static ClienteModel criaClienteModel() {
        var clienteModel = new ClienteModel();
        clienteModel.setId(ID_DELETA_TESTE);
        clienteModel.setNome("Involker");
        clienteModel.setEmail("involker20dedosprajogar@dota.com");
        clienteModel.setSenha("sedeixarfarmarehgg");
        clienteModel.setTelefone("61999999999");
        clienteModel.setCpf("957.601.860-94");
        clienteModel.setDataNascimento(LocalDate.of(2013, 7, 9));
        return clienteModel;
    }
}
