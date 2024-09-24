package br.com.grupo27.tech.challenge.reserva.application.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockBean {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}
