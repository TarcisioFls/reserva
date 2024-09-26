package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request.CriarAvaliacaoRequest;
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

    private CriarAvaliacaoRequest avaliacaoRequest;

    private static final String ENDPOINT_AVALIACAO = "http://localhost:8080/avaliacoes";

    @Dado("que tenho uma reserva")
    public void queTenhoUmaReserva() {
        avaliacaoRequest = getCriarAvaliacaoRequest();
    }

    @Quando("criar uma nova avaliacao para uma reserva")
    public void criarUmaNovaAvaliacaoParaUmaReserva() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(avaliacaoRequest)
                .when()
                .post(ENDPOINT_AVALIACAO);
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

}
