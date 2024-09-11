package br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.ListarTodosRestaurantesDados.getPageRestaurante;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListarTodosRestaurantePresenterImplTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosRestaurantePresenterImpl listarTodosRestaurantePresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testePageRestauranteEmPageTodosRestauranteOutput() {

        var paginacaoRestaurante = getPageRestaurante();

        var resultado = listarTodosRestaurantePresenter.pageRestauranteEmPageTodosRestauranteOutput(paginacaoRestaurante);


        assertAll("Teste Paginação",
                () -> assertNotNull(resultado),
                () -> assertNotNull(resultado.getContent()),
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste ListarTodosRestaurentesOutput",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0).getId())),
                () -> assertEquals("Restaurante Akira", requireNonNull(resultado.getContent().get(0).getNome())),
                () -> assertEquals("66c67aa035ed1f735450b7a3", requireNonNull(resultado.getContent().get(1).getId())),
                () -> assertEquals("Restaurante Taco Taca", requireNonNull(resultado.getContent().get(1).getNome()))
        );

    }

    @Test
    void testePageTodosRestaurantesOutputEmPageRestauranteResponse() {

            var todosRestaurantesOutput = ListarTodosRestaurantesDados.getPageTodosRestaurantesOutput();

            var resultado = listarTodosRestaurantePresenter.pageTodosRestaurantesOutputEmPageRestauranteResponse(todosRestaurantesOutput);

            assertAll("Teste Paginação",
                    () -> assertNotNull(resultado),
                    () -> assertNotNull(resultado.getContent()),
                    () -> assertEquals(2, requireNonNull(resultado.getContent()).size()),
                    () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                    () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                    () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                    () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
            );

            assertAll("Teste RestauranteResponse",
                    () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0).getId())),
                    () -> assertEquals("Restaurante Akira", requireNonNull(resultado.getContent().get(0).getNome())),
                    () -> assertEquals("66c67aa035ed1f735450b7a3", requireNonNull(resultado.getContent().get(1).getId())),
                    () -> assertEquals("Restaurante Taco Taca", requireNonNull(resultado.getContent().get(1).getNome()))
            );
    }
}