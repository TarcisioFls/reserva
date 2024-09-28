package br.com.grupo27.tech.challenge.reserva.util;

import io.gatling.javaapi.core.OpenInjectionStep;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;

@NoArgsConstructor
public class PerformanceUtils {

    public static OpenInjectionStep.RampRate.@NotNull RampRateOpenInjectionStep getRampUp() {
        return rampUsersPerSec(2).to(10).during(Duration.ofSeconds(10));
    }

    public static OpenInjectionStep.ConstantRate.@NotNull ConstantRateOpenInjectionStep getConstantRate() {
        return constantUsersPerSec(10).during(Duration.ofSeconds(20));
    }

    public static OpenInjectionStep.RampRate.@NotNull RampRateOpenInjectionStep getRampDown() {
        return rampUsersPerSec(10).to(2).during(Duration.ofSeconds(10));
    }

}
