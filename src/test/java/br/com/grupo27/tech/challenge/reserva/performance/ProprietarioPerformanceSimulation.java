package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ProprietarioPerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarProprietarioRequest = http("request: criar proprietario")
            .post("/proprietarios")
            .body(StringBody(session -> gson.toJson(getCriarProprietarioUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("proprietarioId"));

    ActionBuilder buscarProprietarioRequest = http("request: buscar proprietario")
            .get("/proprietarios/#{proprietarioId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder atualizarProprietarioRequest = http("request: atualizar proprietario")
            .put("/proprietarios/#{proprietarioId}")
            .body(StringBody(session -> {
                var request = getCriarProprietarioUnicoRequest();
                request.setNome("Performance");
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.nome").is("Performance"));

    ActionBuilder deletarProprietarioRequest = http("request: deletar proprietario")
            .delete("/proprietarios/#{proprietarioId}")
            .check(status().is(HttpStatus.NO_CONTENT.value()));

    ScenarioBuilder scenarioOperacoesProprietario = scenario("operacoes proprietario")
            .exec(criarProprietarioRequest)
            .exec(buscarProprietarioRequest)
            .exec(atualizarProprietarioRequest)
            .exec(deletarProprietarioRequest);

    @Override
    public PopulationBuilder getSimulationConfig() {
        return scenarioOperacoesProprietario.injectOpen(
                PerformanceUtils.getRampUp(),
                PerformanceUtils.getConstantRate(),
                PerformanceUtils.getRampDown()
        );
    }

}
