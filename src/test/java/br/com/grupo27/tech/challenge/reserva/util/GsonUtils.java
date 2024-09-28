package br.com.grupo27.tech.challenge.reserva.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
public class GsonUtils {

    public static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime .class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate .class, new LocalDateAdapter())
                .create();
    }

}
