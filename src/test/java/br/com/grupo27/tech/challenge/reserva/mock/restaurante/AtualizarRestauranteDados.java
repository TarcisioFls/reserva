package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.CASEIRA;

public interface AtualizarRestauranteDados {

    String ID_TESTE = "66c67aa035ed1f735450b7a2";

    static AtualizarRestauranteRequest getAtualizarRestauranteRequest() {
        return criaRequest();
    }

    static AtualizarRestauranteInput getAtualizarRestauranteInput() {
        return criaInput();
    }

    static AtualizarRestauranteOutput getAtualizarRestauranteOutput() {
        return criaOutput();
    }

    static Restaurante getRestaurante() {
        return criaRestaurante();
    }

    private static AtualizarRestauranteOutput criaOutput() {
        var output = new AtualizarRestauranteOutput();
        output.setId(ID_TESTE);
        output.setNome(criaRestaurante().getNome());
        output.setDescricao(criaRestaurante().getDescricao());
        output.setLocalizacao(criaRestaurante().getLocalizacao());
        output.setHoraAbertura(criaRestaurante().getHoraAbertura());
        output.setHoraFechamento(criaRestaurante().getHoraFechamento());
        output.setCapacidade(criaRestaurante().getCapacidade());
        output.setTipoCozinhaList(criaListaTpoCozinha());
        output.setProprietario(criaProprietario());

        return output;
    }

    private static AtualizarRestauranteInput criaInput() {
        var input = new AtualizarRestauranteInput();
        input.setId(ID_TESTE);
        input.setNome(criaRestaurante().getNome());
        input.setDescricao(criaRestaurante().getDescricao());
        input.setLocalizacao(criaRestaurante().getLocalizacao());
        input.setHoraAbertura(criaRestaurante().getHoraAbertura());
        input.setHoraFechamento(criaRestaurante().getHoraFechamento());
        input.setCapacidade(criaRestaurante().getCapacidade());
        input.setTipoCozinhaList(criaListaTpoCozinha());
        input.setProprietario(criaProprietario());

        return input;
    }

    private static AtualizarRestauranteRequest criaRequest() {
        var request = new AtualizarRestauranteRequest();
        request.setNome(criaRestaurante().getNome());
        request.setDescricao(criaRestaurante().getDescricao());
        request.setLocalizacao(criaRestaurante().getLocalizacao());
        request.setHoraAbertura(criaRestaurante().getHoraAbertura());
        request.setHoraFechamento(criaRestaurante().getHoraFechamento());
        request.setCapacidade(criaRestaurante().getCapacidade());
        request.setTipoCozinhaList(criaListaTpoCozinha());
        request.setProprietario(criaProprietario());

        return request;
    }

    private static List<TipoCozinha> criaListaTpoCozinha() {
        return List.of(CASEIRA);
    }

    private static Proprietario criaProprietario() {
        return new Proprietario();
    }

    private static Restaurante criaRestaurante() {
        var restaurante = new Restaurante();
        restaurante.setNome("Rikimaru");
        restaurante.setDescricao("Fica Inv, Roubadão");
        restaurante.setLocalizacao("Não sabemos, pq ta inv");
        restaurante.setHoraAbertura(LocalTime.of(1, 0));
        restaurante.setHoraFechamento(LocalTime.of(19, 59));
        restaurante.setCapacidade(50);
        restaurante.setTipoCozinhaList(criaListaTpoCozinha());
        restaurante.setProprietario(criaProprietario());

        return restaurante;
    }
}