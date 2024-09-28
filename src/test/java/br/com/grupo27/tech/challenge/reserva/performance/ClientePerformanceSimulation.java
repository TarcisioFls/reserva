package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ClientePerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarClienteRequest = http("request: criar cliente")
            .post("/clientes")
            .body(StringBody(session -> gson.toJson(getCriarClienteUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("clienteId"));

    ActionBuilder buscarClienteRequest = http("request: buscar cliente")
            .get("/clientes/#{clienteId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder atualizarClienteRequest = http("request: atualizar cliente")
            .put("/clientes/#{clienteId}")
            .body(StringBody(session -> {
                var request = getCriarClienteUnicoRequest();
                request.setNome("Performance");
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.nome").is("Performance"));

    ActionBuilder deletarClienteRequest = http("request: deletar cliente")
            .delete("/clientes/#{clienteId}")
            .check(status().is(HttpStatus.NO_CONTENT.value()));

    ScenarioBuilder scenarioOperacoesCliente = scenario("operacoes cliente")
            .exec(criarClienteRequest)
            .exec(buscarClienteRequest)
            .exec(atualizarClienteRequest)
            .exec(deletarClienteRequest);

    @Override
    public PopulationBuilder getSimulationConfig() {
        return scenarioOperacoesCliente.injectOpen(
                PerformanceUtils.getRampUp(),
                PerformanceUtils.getConstantRate(),
                PerformanceUtils.getRampDown()
        );
    }

}
