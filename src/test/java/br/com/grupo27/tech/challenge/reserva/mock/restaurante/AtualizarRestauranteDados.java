package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.request.AtualizarRestauranteRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.AtualizarRestauranteOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.CASEIRA;

public interface AtualizarRestauranteDados {

    String ID_TESTE = "66c67aa035ed1f735450b7a2";
    String ID_PROPRIETARIO_TESTE = "99d95aa035ed1f735450b7b2";

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

    static RestauranteResponse getRestauranteResponse() {
        return criaRestauranteResponse();
    }

    static RestauranteModel getRestauranteModel() {
        return criaRestauranteModel();
    }

    private static RestauranteModel criaRestauranteModel() {
        var restauranteModel = new RestauranteModel();
        restauranteModel.setId(criaRestaurante().getId());
        restauranteModel.setNome(criaRestaurante().getNome());
        restauranteModel.setDescricao(criaRestaurante().getDescricao());
        restauranteModel.setLocalizacao(criaRestaurante().getLocalizacao());
        restauranteModel.setHoraAbertura(criaRestaurante().getHoraAbertura());
        restauranteModel.setHoraFechamento(criaRestaurante().getHoraFechamento());
        restauranteModel.setCapacidade(criaRestaurante().getCapacidade());
        restauranteModel.setTipoCozinhaList(criaListaTpoCozinha());
        restauranteModel.setProprietarioId(criaProprietarioModel().getId());

        return restauranteModel;
    }

    private static ProprietarioModel criaProprietarioModel() {
        var proprietarioModel = new ProprietarioModel();
        proprietarioModel.setId(ID_PROPRIETARIO_TESTE);
        return proprietarioModel;
    }

    private static RestauranteResponse criaRestauranteResponse() {
        var response = new RestauranteResponse();
        response.setId(criaRestaurante().getId());
        response.setNome(criaRestaurante().getNome());
        response.setDescricao(criaRestaurante().getDescricao());
        response.setLocalizacao(criaRestaurante().getLocalizacao());
        response.setHoraAbertura(criaRestaurante().getHoraAbertura());
        response.setHoraFechamento(criaRestaurante().getHoraFechamento());
        response.setCapacidade(criaRestaurante().getCapacidade());
        response.setTipoCozinhaList(criaListaTpoCozinha());
        response.setProprietarioId(criaProprietario());

        return response;
    }

    private static AtualizarRestauranteOutput criaOutput() {
        var output = new AtualizarRestauranteOutput();
        output.setId(criaRestaurante().getId());
        output.setNome(criaRestaurante().getNome());
        output.setDescricao(criaRestaurante().getDescricao());
        output.setLocalizacao(criaRestaurante().getLocalizacao());
        output.setHoraAbertura(criaRestaurante().getHoraAbertura());
        output.setHoraFechamento(criaRestaurante().getHoraFechamento());
        output.setCapacidade(criaRestaurante().getCapacidade());
        output.setTipoCozinhaList(criaListaTpoCozinha());
        output.setProprietarioId(criaProprietario());

        return output;
    }

    private static AtualizarRestauranteInput criaInput() {
        var input = new AtualizarRestauranteInput();
        input.setId(criaRestaurante().getId());
        input.setNome(criaRestaurante().getNome());
        input.setDescricao(criaRestaurante().getDescricao());
        input.setLocalizacao(criaRestaurante().getLocalizacao());
        input.setHoraAbertura(criaRestaurante().getHoraAbertura());
        input.setHoraFechamento(criaRestaurante().getHoraFechamento());
        input.setCapacidade(criaRestaurante().getCapacidade());
        input.setTipoCozinhaList(criaListaTpoCozinha());
        input.setProprietarioId(criaProprietario());

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
        request.setProprietarioId(criaProprietario());

        return request;
    }

    private static List<TipoCozinha> criaListaTpoCozinha() {
        return List.of(CASEIRA);
    }

    private static String criaProprietario() {
        var proprietario = new ProprietarioModel();
        proprietario.setId(ID_PROPRIETARIO_TESTE);

        return proprietario.getId();
    }

    private static Restaurante criaRestaurante() {
        var restaurante = new Restaurante();
        restaurante.setId(ID_TESTE);
        restaurante.setNome("Rikimaru");
        restaurante.setDescricao("Fica Inv, Roubadão");
        restaurante.setLocalizacao("Não sabemos, pq ta inv");
        restaurante.setHoraAbertura(LocalTime.of(1, 0));
        restaurante.setHoraFechamento(LocalTime.of(19, 59));
        restaurante.setCapacidade(50);
        restaurante.setTipoCozinhaList(criaListaTpoCozinha());
        restaurante.setProprietarioId(criaProprietario());

        return restaurante;
    }
}
