package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import br.com.grupo27.tech.challenge.reserva.application.controllers.reserva.response.ReservaResponse;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.response.RestauranteResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaValidRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteUnicoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ReservaStepDefinition {

    private Response response;

    private ClienteResponse clienteResponse;

    private RestauranteResponse restauranteResponse;

    private ReservaResponse reservaResponse;

    private static final String ENDPOINT_BASE = "http://localhost:8080";

    private static final String ENDPOINT_CLIENTE = ENDPOINT_BASE + "/clientes";

    private static final String ENDPOINT_RESTAURANTE = ENDPOINT_BASE + "/restaurantes";

    private static final String ENDPOINT_RESERVA = ENDPOINT_BASE + "/reservas";

    @Dado("um cliente ja criado para reserva")
    public void umClienteJaCriadoParaReserva() {
        clienteResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(getCriarClienteUnicoRequest())
                .when()
                .post(ENDPOINT_CLIENTE)
                .then()
                .extract()
                .as(ClienteResponse.class);
    }

    @Dado("um restaurante ja criado para reserva")
    public void umRestauranteJaCriadoParaReserva() {
        restauranteResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(getCriarRestauranteUnicoRequest())
                .when()
                .post(ENDPOINT_RESTAURANTE)
                .then()
                .extract()
                .as(RestauranteResponse.class);
    }

    @Dado("uma reserva ja criada")
    public void umaReservaJaCriada() {
        umClienteJaCriadoParaReserva();
        umRestauranteJaCriadoParaReserva();
        reservaResponse = efetuarRequisicaoDeCriacaoDeReserva();
    }

    @Quando("efetuar requisicao de criacao de reserva")
    public ReservaResponse efetuarRequisicaoDeCriacaoDeReserva() {
        var request = getCriarReservaValidRequest();
        request.setClienteId(clienteResponse.getId());
        request.setRestauranteId(restauranteResponse.getId());
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_RESERVA);
        return response.then().extract().as(ReservaResponse.class);
    }

    @Entao("a reserva e criada com sucesso")
    public void aReservaECriadaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"));
    }

    @Quando("atualizar a reserva atraves do id")
    public void atualizarAReservaAtualizaDoId() {
        reservaResponse.setQuantidadePessoas(1);
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(reservaResponse)
                .when()
                .put(ENDPOINT_RESERVA + "/{id}", reservaResponse.getId());
    }

    @Entao("reserva atualizada com sucesso")
    public void reservaAtualizadaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"));
    }

    @Quando("realizar a busca de reservas")
    public void realizarABuscaDeReservas() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESERVA);
    }

    @Entao("reservas sao exibidas com sucesso")
    public void reservasSaoExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaPageResponse.schema.json"));
    }

    @Quando("efetuar requisicao de busca de reserva por id")
    public void efetuarRequisicaoDeBuscaDeReservaPorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESERVA + "/{id}", reservaResponse.getId());
    }

    @Entao("reserva e exibida com sucesso")
    public void reservaEExibidaComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaResponse.schema.json"));
    }

    @Quando("efetuar requisicao de busca de reservas por restaurante")
    public void efetuarRequisicaoDeBuscaDeReservasPorRestaurante() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESERVA + "/restaurante?restauranteId={id}", restauranteResponse.getId());
    }

    @Entao("reservas do restaurante sao exibidas com sucesso")
    public void reservasDoRestauranteSaoExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaPageResponse.schema.json"));
    }

    @Quando("efetuar requisicao de busca de reservas por cliente")
    public void efetuarRequisicaoDeBuscaDeReservasPorCliente() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_RESERVA + "/cliente?clienteId={id}", clienteResponse.getId());
    }

    @Entao("reservas do cliente sao exibidas com sucesso")
    public void reservasDoClienteSaoExibidasComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/reserva/reservaPageResponse.schema.json"));
    }

}
