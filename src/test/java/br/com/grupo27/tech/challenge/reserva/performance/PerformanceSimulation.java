package br.com.grupo27.tech.challenge.reserva.performance;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.util.Arrays;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.http.HttpDsl.http;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    .header("Content-Type", "application/json");

    private final List<GatlingSimulation> simulations = Arrays.asList(
            new AvaliacaoPerformanceSimulation(),
            new ClientePerformanceSimulation(),
            new ProprietarioPerformanceSimulation(),
            new ReservaPerformanceSimulation(),
            new RestaurantePerformanceSimulation()
    );

    private List<PopulationBuilder> getSimulationConfigs() {
        return simulations.stream().map(GatlingSimulation::getSimulationConfig).toList();
    }

    {
        setUp(getSimulationConfigs()).protocols(httpProtocol).assertions(global().responseTime().max().lt(200));
    }

}
