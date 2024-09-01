package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.CriarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.CriarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.CriarRestauranteOutput;

import java.time.LocalTime;
import java.util.ArrayList;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;

public interface CriarRestauranteDados {

    static CriarRestauranteRequest getCriarRestauranteRequest() {
        var dados = getDadosRestaurante();
        return new CriarRestauranteRequest(dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietario());
    }

    static CriarRestauranteInput getCriarRestauranteInput() {
        var dados = getDadosRestaurante();
        return new CriarRestauranteInput(dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietario());
    }

    static CriarRestauranteOutput getCriarRestauranteOutput() {
        var dados = getDadosRestaurante();
        return new CriarRestauranteOutput(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietario());
    }

    static RestauranteResponse getRestauranteResponse() {
        var dados = getDadosRestaurante();
        return new RestauranteResponse(dados.getId(), dados.getNome(), dados.getDescricao(), dados.getLocalizacao(), dados.getHoraAbertura(),
                dados.getHoraFechamento(), dados.getCapacidade(), dados.getTipoCozinhaList(), dados.getProprietario());
    }

    private static Restaurante getDadosRestaurante() {
        var tipoCozinhaList = new ArrayList<TipoCozinha>();
        tipoCozinhaList.add(JAPONESA);
        var proprietario = new Proprietario();
        var restaurante = new Restaurante();
        restaurante.setId("66c67aa035ed1f735450b7a2");
        restaurante.setNome("Nome");
        restaurante.setDescricao("Descricao");
        restaurante.setLocalizacao("Localizacao");
        restaurante.setHoraAbertura(LocalTime.of(7, 0));
        restaurante.setHoraFechamento(LocalTime.of(22, 0));
        restaurante.setCapacidade(100);
        restaurante.setTipoCozinhaList(tipoCozinhaList);
        restaurante.setProprietario(proprietario);
        return restaurante;
    }
}
