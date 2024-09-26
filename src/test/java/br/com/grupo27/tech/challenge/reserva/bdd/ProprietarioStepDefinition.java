package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario.response.ProprietarioResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioUnicoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ProprietarioStepDefinition {

    private Response response;

    private ProprietarioResponse proprietarioResponse;

    private static final String ENDPOINT_PROPRIETARIO = "http://localhost:8080/proprietarios";

    @Dado("um proprietario ja criado")
    public void umProprietarioJaCriado() {
        proprietarioResponse = efetuarRequisicaoParaCriarUmNovoProprietario();
    }

    @Quando("efetuar requisicao para criar um novo proprietario")
    public ProprietarioResponse efetuarRequisicaoParaCriarUmNovoProprietario() {
        var request = getCriarProprietarioUnicoRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_PROPRIETARIO);
        return response.then().extract().as(ProprietarioResponse.class);
    }

    @Entao("proprietario criado com sucesso")
    public void proprietarioCriadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/proprietario/proprietarioResponse.schema.json"));
    }

    @Quando("atualizar o proprietario atraves do id")
    public void atualizarOProprietarioAtravesDoId() {
        proprietarioResponse.setNome("nome bdd");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(proprietarioResponse)
                .when()
                .put(ENDPOINT_PROPRIETARIO + "/{id}", proprietarioResponse.getId());
    }

    @Entao("proprietario atualizado com sucesso")
    public void proprietarioAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/proprietario/proprietarioResponse.schema.json"));
    }

    @Quando("realizar a busca de proprietarios")
    public void realizarABuscaDeproprietarios() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_PROPRIETARIO);
    }

    @Entao("proprietarios sao exibidos com sucesso")
    public void proprietariosSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/proprietario/proprietarioPageResponse.schema.json"))
                .body("page.number", equalTo(0))
                .body("page.size", equalTo(50));
    }

    @Quando("efetuar requisicao de busca de proprietario por id")
    public void efetuarRequisicaoDeBuscaDeProprietarioPorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_PROPRIETARIO + "/{id}", proprietarioResponse.getId());
    }

    @Entao("proprietario e exibido com sucesso")
    public void proprietarioEExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/proprietario/proprietarioResponse.schema.json"));
    }

    @Quando("efetuar requisicao de delecao de proprietario por id")
    public void efetuarRequisicaoDeDelecaoDeProprietarioPorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_PROPRIETARIO + "/{id}", proprietarioResponse.getId());
    }

    @Entao("proprietario e deletado com sucesso")
    public void proprietarioEDeletadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
