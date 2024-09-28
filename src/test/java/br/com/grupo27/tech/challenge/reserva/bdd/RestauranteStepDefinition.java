package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteUnicoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class RestauranteStepDefinition {

    private Response response;

    private RestauranteResponse restauranteResponse;

    private static final String ENDPOINT_RESTAURANTE = "http://localhost:8080/restaurantes";

    @Dado("um restaurante ja criado")
    public void umRestauranteJaCriado() {
        restauranteResponse = efetuarRequisicaoParaCriarUmNovoRestaurante();
    }

    @Quando("efetuar requisicao para criar um novo restaurante")
    public RestauranteResponse efetuarRequisicaoParaCriarUmNovoRestaurante() {
        var request = getCriarRestauranteUnicoRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_RESTAURANTE);
        return response.then().extract().as(RestauranteResponse.class);
    }

    @Entao("restaurante criado com sucesso")
    public void restauranteCriadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restauranteResponse.schema.json"));
    }

    @Quando("atualizar o restaurante atraves do id")
    public void atualizarORestauranteAtravesDoId() {
        restauranteResponse.setNome("nome bdd");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restauranteResponse)
                .when()
                .put(ENDPOINT_RESTAURANTE + "/{id}", restauranteResponse.getId());
    }

    @Entao("restaurante atualizado com sucesso")
    public void restauranteAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restauranteResponse.schema.json"));
    }

    @Quando("realizar a busca de restaurantes")
    public void realizarABuscaDeRestaurantes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESTAURANTE);
    }

    @Entao("restaurantes sao exibidos com sucesso")
    public void restaurantesSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurantePageResponse.schema.json"))
                .body("page.number", equalTo(0))
                .body("page.size", equalTo(50));
    }

    @Quando("efetuar requisicao de busca de restaurante por id")
    public void efetuarRequisicaoDeBuscaDeRestaurantePorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESTAURANTE + "/{id}", restauranteResponse.getId());
    }

    @Entao("restaurante e exibido com sucesso")
    public void restauranteEExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restauranteResponse.schema.json"));
    }

    @Quando("realizar a busca de restaurantes com filtro")
    public void realizarABuscaDeRestaurantesComFiltro() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESTAURANTE + "/buscar/{buscar}", restauranteResponse.getNome());
    }

    @Entao("restaurantes filtrados sao exibidos com sucesso")
    public void restaurantesFiltradosSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/restaurante/restaurantePageResponse.schema.json"))
                .body("page.number", equalTo(0))
                .body("page.size", equalTo(50));
    }

    @Quando("efetuar requisicao de delecao de restaurante por id")
    public void efetuarRequisicaoDeDelecaoDeRestaurantePorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_RESTAURANTE + "/{id}", restauranteResponse.getId());
    }

    @Entao("restaurante e deletado com sucesso")
    public void restauranteEDeletadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
