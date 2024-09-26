package br.com.grupo27.tech.challenge.reserva.performance;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import java.time.Duration;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ClientePerformanceSimulation extends PerformanceSimulation {

    ActionBuilder criarClienteRequest = http("request: criar cliente")
            .post("/clientes")
            .body(StringBody(session -> {
                try {
                    return objectMapper.writeValueAsString(getCriarClienteUnicoRequest());
                } catch (JsonProcessingException e) {
                    System.err.println("Error processing JSON: " + e.getMessage());
                    return "{}";
                }
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("clienteId"));

    ActionBuilder buscarClienteRequest = http("request: buscar cliente")
            .get("/clientes/#{clienteId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder atualizarClienteRequest = http("request: atualizar cliente")
            .put("/clientes/#{clienteId}")
            .body(StringBody(session -> {
                try {
                    var request = getCriarClienteUnicoRequest();
                    request.setNome("Performance");
                    return objectMapper.writeValueAsString(request);
                } catch (JsonProcessingException e) {
                    System.err.println("Error processing JSON: " + e.getMessage());
                    return "{}";
                }
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.nome").is("Performance"));

    ActionBuilder deletarClienteRequest = http("request: deletar cliente")
            .delete("/clientes/#{clienteId}")
            .check(status().is(HttpStatus.NO_CONTENT.value()));

    ScenarioBuilder scenarioCriarCliente = scenario("criar cliente")
            .exec(criarClienteRequest);

    ScenarioBuilder scenarioBuscarCliente = scenario("buscar cliente")
            .exec(criarClienteRequest)
            .exec(buscarClienteRequest);

    ScenarioBuilder scenarioAtualizarCliente = scenario("atualizar cliente")
            .exec(criarClienteRequest)
            .exec(atualizarClienteRequest);

    ScenarioBuilder scenarioDeletarCliente = scenario("deletar cliente")
            .exec(criarClienteRequest)
            .exec(deletarClienteRequest);

    {
        setUp(
                scenarioCriarCliente.injectOpen(
                        rampUsersPerSec(1)
                                .to(2)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(2)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(2)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                scenarioBuscarCliente.injectOpen(
                        rampUsersPerSec(1)
                                .to(4)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(4)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(4)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                scenarioAtualizarCliente.injectOpen(
                        rampUsersPerSec(1)
                                .to(4)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(4)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(4)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                ),
                scenarioDeletarCliente.injectOpen(
                        rampUsersPerSec(1)
                                .to(4)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(4)
                                .during(Duration.ofSeconds(20)),
                        rampUsersPerSec(4)
                                .to(1)
                                .during(Duration.ofSeconds(10))
                )
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50)
                );
    }
}
