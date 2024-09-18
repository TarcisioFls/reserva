package br.com.grupo27.tech.challenge.reserva.mock.restaurante;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Restaurante;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import br.com.grupo27.tech.challenge.reserva.domain.output.restaurante.BuscarRestaurantePorIdOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.ProprietarioModel;
import br.com.grupo27.tech.challenge.reserva.infra.model.RestauranteModel;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.CASEIRA;

public interface BuscarRestaurantePorIdDados {

    String ID_BUSCAR_TESTE = "66c67aa035ed1f735450b7a2";
    String ID_PROPRIETARIO_TESTE = "99d95aa035ed1f735450b7b2";

    static BuscarRestaurantePorIdOutput getBuscarRestaurantePorIdOutput() {
        return criaBuscarRestaurantePorIdOutput();
    }

    static RestauranteResponse getRestauranteResponse() {
        return criaRestauranteResponse();
    }

    static RestauranteModel getRestauranteModel() {
        return criaRestauranteModel();
    }

    static Restaurante getRestaurante() {
        return criaRestaurante();
    }

    private static RestauranteModel criaRestauranteModel() {
        var restauranteModel = new RestauranteModel();
        restauranteModel.setId(ID_BUSCAR_TESTE);
        restauranteModel.setId(criaRestaurante().getId());
        restauranteModel.setNome(criaRestaurante().getNome());
        restauranteModel.setDescricao(criaRestaurante().getDescricao());
        restauranteModel.setLocalizacao(criaRestaurante().getLocalizacao());
        restauranteModel.setHoraAbertura(criaRestaurante().getHoraAbertura());
        restauranteModel.setHoraFechamento(criaRestaurante().getHoraFechamento());
        restauranteModel.setCapacidade(criaRestaurante().getCapacidade());
        restauranteModel.setTipoCozinhaList(criaListaTpoCozinha());
        restauranteModel.setProprietarioId(criaProprietarioModel());

        return restauranteModel;
    }

    private static RestauranteResponse criaRestauranteResponse() {
        var restauranteResponse = new RestauranteResponse();
        restauranteResponse.setId(ID_BUSCAR_TESTE);
        restauranteResponse.setId(criaRestaurante().getId());
        restauranteResponse.setNome(criaRestaurante().getNome());
        restauranteResponse.setDescricao(criaRestaurante().getDescricao());
        restauranteResponse.setLocalizacao(criaRestaurante().getLocalizacao());
        restauranteResponse.setHoraAbertura(criaRestaurante().getHoraAbertura());
        restauranteResponse.setHoraFechamento(criaRestaurante().getHoraFechamento());
        restauranteResponse.setCapacidade(criaRestaurante().getCapacidade());
        restauranteResponse.setTipoCozinhaList(criaListaTpoCozinha());
        restauranteResponse.setProprietarioId(criaProprietarioModel());

        return restauranteResponse;
    }

    private static BuscarRestaurantePorIdOutput criaBuscarRestaurantePorIdOutput() {

        var buscarRestaurantePorIdOutput = new BuscarRestaurantePorIdOutput();
        buscarRestaurantePorIdOutput.setId(ID_BUSCAR_TESTE);
        buscarRestaurantePorIdOutput.setId(criaRestaurante().getId());
        buscarRestaurantePorIdOutput.setNome(criaRestaurante().getNome());
        buscarRestaurantePorIdOutput.setDescricao(criaRestaurante().getDescricao());
        buscarRestaurantePorIdOutput.setLocalizacao(criaRestaurante().getLocalizacao());
        buscarRestaurantePorIdOutput.setHoraAbertura(criaRestaurante().getHoraAbertura());
        buscarRestaurantePorIdOutput.setHoraFechamento(criaRestaurante().getHoraFechamento());
        buscarRestaurantePorIdOutput.setCapacidade(criaRestaurante().getCapacidade());
        buscarRestaurantePorIdOutput.setTipoCozinhaList(criaListaTpoCozinha());
        buscarRestaurantePorIdOutput.setProprietarioId(criaProprietarioModel());

        return buscarRestaurantePorIdOutput;
    }

    private static Restaurante criaRestaurante() {
        var restaurante = new Restaurante();
        restaurante.setId(ID_BUSCAR_TESTE);
        restaurante.setNome("Rikimaru");
        restaurante.setDescricao("Fica Inv, Roubadão");
        restaurante.setLocalizacao("Não sabemos, pq ta inv");
        restaurante.setHoraAbertura(LocalTime.of(1, 0));
        restaurante.setHoraFechamento(LocalTime.of(19, 59));
        restaurante.setCapacidade(50);
        restaurante.setTipoCozinhaList(criaListaTpoCozinha());
        restaurante.setProprietarioId(criaProprietarioModel());

        return restaurante;
    }

    private static List<TipoCozinha> criaListaTpoCozinha() {
        return List.of(CASEIRA);
    }

    private static String criaProprietarioModel() {
        var proprietario = new ProprietarioModel();
        proprietario.setId(ID_PROPRIETARIO_TESTE);

        return proprietario.getId();
    }
}
