package br.com.grupo27.tech.challenge.reserva.bdd;

import br.com.grupo27.tech.challenge.reserva.application.controllers.cliente.response.ClienteResponse;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ClienteStepDefinition {

    private Response response;

    private ClienteResponse clienteResponse;

    private static final String ENDPOINT_CLIENTE = "http://localhost:8080/clientes";

    @Dado("um cliente ja criado")
    public void umClienteJaCriado() {
        clienteResponse = efetuarRequisicaoParaCriarUmNovoCliente();
    }

    @Quando("efetuar requisicao para criar um novo cliente")
    public ClienteResponse efetuarRequisicaoParaCriarUmNovoCliente() {
        var request = getCriarClienteUnicoRequest();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(ENDPOINT_CLIENTE);
        return response.then().extract().as(ClienteResponse.class);
    }

    @Entao("cliente criado com sucesso")
    public void clienteCriadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/cliente/clienteResponse.schema.json"));
    }

    @Quando("atualizar o cliente atraves do id")
    public void atualizarOClienteAtravesDoId() {
        clienteResponse.setNome("nome bdd");
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteResponse)
                .when()
                .put(ENDPOINT_CLIENTE + "/{id}", clienteResponse.getId());
    }

    @Entao("cliente atualizado com sucesso")
    public void clienteAtualizadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/cliente/clienteResponse.schema.json"));
    }

    @Quando("realizar a busca de clientes")
    public void realizarABuscaDeClientes() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTE);
    }

    @Entao("clientes sao exibidos com sucesso")
    public void clientesSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/cliente/clientePageResponse.schema.json"))
                .body("page.number", equalTo(0))
                .body("page.size", equalTo(50));
    }

    @Quando("efetuar requisicao de busca de cliente por id")
    public void efetuarRequisicaoDeBuscaDeClientePorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(ENDPOINT_CLIENTE + "/{id}", clienteResponse.getId());
    }

    @Entao("cliente e exibido com sucesso")
    public void clienteEExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/cliente/clienteResponse.schema.json"));
    }

    @Quando("efetuar requisicao de delecao de cliente por id")
    public void efetuarRequisicaoDeDelecaoDeClientePorId() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(ENDPOINT_CLIENTE + "/{id}", clienteResponse.getId());
    }

    @Entao("cliente e deletado com sucesso")
    public void clienteEDeletadoComSucesso() {
        response.then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
