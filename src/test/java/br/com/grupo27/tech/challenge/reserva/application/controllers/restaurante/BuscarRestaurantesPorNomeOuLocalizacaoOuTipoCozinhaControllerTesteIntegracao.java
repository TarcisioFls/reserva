package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha.JAPONESA;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModel;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
public class BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaControllerTesteIntegracao {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private BuscarRestaurantesPorNomeOuLocalizacaoOuTipoCozinhaController buscarRestaurantesController;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        restauranteRepository.save(getRestauranteModel());
    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
    }

    @Test
    void testeBuscarRestaurantesPorNome() {
        var busca = "mag";

        var resultado = buscarRestaurantesController.buscarRestaurantes(busca);

        assertNotNull(resultado);
        assertNotNull(resultado.getBody());

        assertAll("Teste paginação",
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(50, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste conteúdo",
                () -> assertEquals("Magina", requireNonNull(resultado.getBody().getContent()).get(0).getNome()),
                () -> assertEquals("do seu lado", requireNonNull(resultado.getBody().getContent()).get(0).getLocalizacao()),
                () -> assertEquals(List.of(JAPONESA), requireNonNull(resultado.getBody().getContent()).get(0).getTipoCozinhaList())
        );
    }

    @Test
    void testeBuscarRestaurantesPorLocalizacao() {
        var busca = "lado";

        var resultado = buscarRestaurantesController.buscarRestaurantes(busca);

        assertNotNull(resultado);
        assertNotNull(resultado.getBody());

        assertAll("Teste paginação",
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(50, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste conteúdo",
                () -> assertEquals("Magina", requireNonNull(resultado.getBody().getContent()).get(0).getNome()),
                () -> assertEquals("do seu lado", requireNonNull(resultado.getBody().getContent()).get(0).getLocalizacao()),
                () -> assertEquals(List.of(JAPONESA), requireNonNull(resultado.getBody().getContent()).get(0).getTipoCozinhaList())
        );
    }

    @Test
    void testeBuscarRestaurantesPorTipoCozinha() {
        var busca = "jap";

        var resultado = buscarRestaurantesController.buscarRestaurantes(busca);

        assertNotNull(resultado);
        assertNotNull(resultado.getBody());

        assertAll("Teste paginação",
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(50, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste conteúdo",
                () -> assertEquals("Magina", requireNonNull(resultado.getBody().getContent()).get(0).getNome()),
                () -> assertEquals("do seu lado", requireNonNull(resultado.getBody().getContent()).get(0).getLocalizacao()),
                () -> assertEquals(List.of(JAPONESA), requireNonNull(resultado.getBody().getContent()).get(0).getTipoCozinhaList())
        );
    }

    @Test
    void testeBuscarRestaurantesPorNomeNaoEncontrado() {
        var busca = "nao existe";

        var resultado = buscarRestaurantesController.buscarRestaurantes(busca);

        assertNotNull(resultado);
        assertNotNull(resultado.getBody());

        assertAll("Teste paginação",
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).totalElements()),
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).number()),
                () -> assertEquals(50, requireNonNull(resultado.getBody().getMetadata()).size()),
                () -> assertEquals(0, requireNonNull(resultado.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste conteúdo",
                () -> assertEquals(0, requireNonNull(resultado.getBody().getContent()).size())
        );
    }

}
