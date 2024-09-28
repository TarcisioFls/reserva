package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AvaliacaoPerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarAvaliacaoRequest = http("request: criar avaliacao")
            .post("/avaliacoes")
            .body(StringBody(session -> gson.toJson(getCriarAvaliacaoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("avaliacaoId"));

    ActionBuilder listarAvaliacaoRequest = http("request: listar avaliacao")
            .get("/avaliacoes")
            .check(status().is(HttpStatus.OK.value()));

    ScenarioBuilder scenarioOperacoesAvaliacao = scenario("operacoes avaliacao")
            .exec(criarAvaliacaoRequest)
            .exec(listarAvaliacaoRequest);

    @Override
    public PopulationBuilder getSimulationConfig() {
        return scenarioOperacoesAvaliacao.injectOpen(
                PerformanceUtils.getRampUp(),
                PerformanceUtils.getConstantRate(),
                PerformanceUtils.getRampDown()
        );
    }

}
