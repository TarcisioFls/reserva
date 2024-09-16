package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaDados.getRestaurante;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaPresenterImpl buscarRestaurantesPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeBuscarRestaurantesOutputEmRestaurantesResponse() {

        var resultado = buscarRestaurantesPresenter.buscarRestaurantesOutputEmRestaurantesResponse(buscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput());

        assertAll("Teste paginação",
                () -> assertNotNull(resultado),
                () -> assertNotNull(resultado.getContent()),
                () -> assertEquals(1, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput",
                () -> assertEquals("77b87aa035ed1f735450b9d6", requireNonNull(resultado.getContent().get(0).getId())),
                () -> assertEquals("Akira", requireNonNull(resultado.getContent().get(0).getNome())),
                () -> assertEquals("Rua 1", requireNonNull(resultado.getContent().get(0).getLocalizacao())),
                () -> assertEquals(List.of(JAPONESA), requireNonNull(resultado.getContent().get(0).getTipoCozinhaList()))
        );
    }

    @Test
    void testeRestauranteEmBuscarRestauranteOutput() {

        var resultado = buscarRestaurantesPresenter.restauranteEmBuscarRestauranteOutput(getRestaurante());

        assertAll("Teste paginação",
                () -> assertNotNull(resultado),
                () -> assertNotNull(resultado.getContent()),
                () -> assertEquals(1, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaOutput",
                () -> assertEquals("77b87aa035ed1f735450b9d6", requireNonNull(resultado.getContent().get(0).getId())),
                () -> assertEquals("Akira", requireNonNull(resultado.getContent().get(0).getNome())),
                () -> assertEquals("Rua 1", requireNonNull(resultado.getContent().get(0).getLocalizacao())),
                () -> assertEquals(List.of(JAPONESA), requireNonNull(resultado.getContent().get(0).getTipoCozinhaList()))
        );
    }

}