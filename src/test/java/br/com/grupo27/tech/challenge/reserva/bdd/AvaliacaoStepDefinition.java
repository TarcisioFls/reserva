package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AvaliacaoStepDefinition {

    private Response response;

    private AvaliacaoResponse avaliacaoResponse;

    private static final String ENDPOINT_AVALIACAO = "http://localhost:8080/avaliacoes";

    @Quando("criar uma nova avaliacao para uma reserva")
    public AvaliacaoResponse criarUmaNovaAvaliacaoParaUmaReserva() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(getCriarAvaliacaoRequest())
                .when()
                .post(ENDPOINT_AVALIACAO);
        return response.then().extract().as(AvaliacaoResponse.class);
    }

    @Entao("cria avaliacao no banco com sucesso")
    public void criaAvaliacaoNoBancoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Entao("deve ser apresentado o resultado")
    public void deveSerApresentadoOResultado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoResponse.schema.json"));
    }

    @Dado("uma avaliacao ja criada")
    public void umaAvaliacaoJaCriada() {
        avaliacaoResponse = criarUmaNovaAvaliacaoParaUmaReserva();
    }

    @Quando("realizar a busca de avaliacoes")
    public void realizarAvaliacoes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_AVALIACAO);
    }

    @Entao("avaliacoes sao exibidas com sucesso")
    public void avaliacoesSaoExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/avaliacao/avaliacaoPageResponse.schema.json"));
    }

}
