package br.com.grupo27.tech.challenge.reserva.performance;

import br.com.grupo27.tech.challenge.reserva.util.GsonUtils;
import br.com.grupo27.tech.challenge.reserva.util.PerformanceUtils;
import com.google.gson.Gson;
import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.springframework.http.HttpStatus;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.getCriarClienteUnicoRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.getCriarReservaValidRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getCriarRestauranteUnicoRequest;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ReservaPerformanceSimulation implements GatlingSimulation {

    private final Gson gson = GsonUtils.buildGson();

    ActionBuilder criarClienteRequest = http("request: criar cliente")
            .post("/clientes")
            .body(StringBody(session -> gson.toJson(getCriarClienteUnicoRequest())))
            .check(status().is(HttpStatus.OK.value()))
            .check(jsonPath("$.id").saveAs("clienteId"));

    ActionBuilder criarRestauranteRequest = http("request: criar restaurante")
            .post("/restaurantes")
            .body(StringBody(session -> {
                var request = getCriarRestauranteUnicoRequest();
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

    ScenarioBuilder scenarioOperacoesReserva = scenario("operacoes reserva")
            .exec(criarClienteRequest)
            .exec(criarRestauranteRequest)
            .exec(criarReservaRequest);

    @Override
    public PopulationBuilder getSimulationConfig() {
        return scenarioOperacoesReserva.injectOpen(
                PerformanceUtils.getRampUp(),
                PerformanceUtils.getConstantRate(),
                PerformanceUtils.getRampDown()
        );
    }

}
