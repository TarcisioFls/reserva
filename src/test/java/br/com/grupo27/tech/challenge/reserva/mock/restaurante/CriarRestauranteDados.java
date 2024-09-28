package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;

import java.util.ArrayList;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;

public interface CriarRestauranteDados {

    static CriarRestauranteRequest getCriarRestauranteUnicoRequest() {
        var dados = getRestaurante();
        return new CriarRestauranteRequest(dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietarioId());
    }

    static CriarRestauranteRequest getCriarRestauranteRequest() {
        var dados = getRestaurante();
        return new CriarRestauranteRequest(dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietarioId());
    }

    static CriarRestauranteInput getCriarRestauranteInput() {
        var dados = getRestaurante();
        return new CriarRestauranteInput(dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietarioId());
    }

    static CriarRestauranteOutput getCriarRestauranteOutput() {
        var dados = getRestaurante();
        return new CriarRestauranteOutput(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietarioId());
    }

    static RestauranteResponse getRestauranteResponse() {
        var dados = getRestaurante();
        return new RestauranteResponse(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietarioId());
    }

    static RestauranteModel getRestauranteModel() {
        var dados = getRestaurante();
        var proprietarioModel = new ProprietarioModel();
        proprietarioModel.setId("44c67aa035ed1f735450b72a");
        return new RestauranteModel(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), proprietarioModel.getId());
    }

    static RestauranteModel getRestauranteModelSemId() {
        var dados = getRestaurante();
        var proprietarioModel = new ProprietarioModel();
        return new RestauranteModel(null, dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), proprietarioModel.getId());
    }

    static Restaurante getRestaurante() {
        var proprietario = new Proprietario();
        proprietario.setId("44c67aa035ed1f735450b72a");
        var tipoCozinhaList = new ArrayList<TipoCozinha>();
        tipoCozinhaList.add(JAPONESA);
        var proprietarioId = proprietario.getId();
        var restaurante = new Restaurante();
        restaurante.setId("66c67aa035ed1f735450b7a2");
        restaurante.setNome("Magina");
        restaurante.setDescricao("Se deixar farmar é GG");
        restaurante.setLocalizacao("do seu lado");
        restaurante.setHoraAbertura("07:00");
        restaurante.setHoraFechamento("22:00");
        restaurante.setCapacidade(100);
        restaurante.setTipoCozinhaList(tipoCozinhaList);
        restaurante.setProprietarioId(proprietarioId);
        return restaurante;
    }
}
