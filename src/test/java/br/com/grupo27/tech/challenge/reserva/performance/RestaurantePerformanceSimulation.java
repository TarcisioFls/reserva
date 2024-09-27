package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RestaurantePerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarRestauranteRequest = http("request: criar restaurante")
            .post("/restaurantes")
            .body(StringBody(session -> gson.toJson(getCriarRestauranteUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("restauranteId"));

    ActionBuilder buscarRestauranteRequest = http("request: buscar restaurante")
            .get("/restaurantes/#{restauranteId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder atualizarRestauranteRequest = http("request: atualizar restaurante")
            .put("/restaurantes/#{restauranteId}")
            .body(StringBody(session -> {
                var request = getCriarRestauranteUnicoRequest();
                request.setNome("Performance");
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.nome").saveAs("Performance"));

    ActionBuilder deletarRestauranteRequest = http("request: deletar restaurante")
            .delete("/restaurantes/#{restauranteId}")
            .check(status().is(HttpStatus.NO_CONTENT.value()));

    ScenarioBuilder scenarioOperacoesRestaurante = scenario("operacoes restaurante")
            .exec(criarRestauranteRequest)
            .exec(buscarRestauranteRequest)
            .exec(atualizarRestauranteRequest)
            .exec(deletarRestauranteRequest);

    @Override
    public PopulationBuilder getSimulationConfig() {
        return scenarioOperacoesRestaurante.injectOpen(
                PerformanceUtils.getRampUp(),
                PerformanceUtils.getConstantRate(),
                PerformanceUtils.getRampDown()
        );
    }

}
