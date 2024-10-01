package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioUnicoRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaValidRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AvaliacaoPerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarClienteRequest = http("request: criar cliente")
            .post("/clientes")
            .body(StringBody(session -> gson.toJson(getCriarClienteUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("clienteId"));

    ActionBuilder criarProprietarioRequest = http("request: criar proprietario")
            .post("/proprietarios")
            .body(StringBody(session -> gson.toJson(getCriarProprietarioUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("proprietarioId"));

    ActionBuilder criarRestauranteRequest = http("request: criar restaurante")
            .post("/restaurantes")
            .body(StringBody(session -> {
                var request = getCriarRestauranteUnicoRequest();
                request.setProprietarioId(session.get("proprietarioId"));
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("restauranteId"));

    ActionBuilder criarReservaRequest = http("request: criar reserva")
            .post("/reservas")
            .body(StringBody(session -> {
                var request = getCriarReservaValidRequest();
                request.setClienteId(session.getString("clienteId"));
                request.setRestauranteId(session.getString("restauranteId"));
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("reservaId"));

    ActionBuilder criarAvaliacaoRequest = http("request: criar avaliacao")
            .post("/avaliacoes")
            .body(StringBody(session -> {
                var request = getCriarAvaliacaoRequest();
                request.setReservaId(session.getString("reservaId"));
                return gson.toJson(request);
            }))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("avaliacaoId"));

    ActionBuilder listarAvaliacaoRestauranteRequest = http("request: listar avaliacao restaurante")
            .get("/avaliacoes/restaurante/#{restauranteId}")
            .check(status().is(HttpStatus.OK.value()));

    ActionBuilder listarAvaliacaoRequest = http("request: listar avaliacao")
            .get("/avaliacoes")
            .check(status().is(HttpStatus.OK.value()));

    ScenarioBuilder scenarioOperacoesAvaliacao = scenario("operacoes avaliacao")
            .exec(criarClienteRequest)
            .exec(criarProprietarioRequest)
            .exec(criarRestauranteRequest)
            .exec(criarReservaRequest)
            .exec(criarAvaliacaoRequest)
            .exec(listarAvaliacaoRestauranteRequest)
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
