package br.com.grupo27.tech.challenge.reserva.integration.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.ListarAvaliacaoPorRestauranteIdController;
import br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante.BuscarRestaurantePorIdController;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
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

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados.getReservaModel;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.CriarRestauranteDados.getRestauranteModelParaTesteDeListarAvaliacaoPorRestaurante;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class ListarAvaliacaoPorRestauranteIdControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ListarAvaliacaoPorRestauranteIdController listarAvaliacaoPorRestauranteIdController;

    @Autowired
    private BuscarRestaurantePorIdController buscarRestaurantePorIdController;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        restauranteRepository.deleteAll();
        restauranteRepository.save(getRestauranteModelParaTesteDeListarAvaliacaoPorRestaurante());
        reservaRepository.deleteAll();
        reservaRepository.save(getReservaModel());
        avaliacaoRepository.deleteAll();
        avaliacaoRepository.save(getAvaliacaoModel1());
        avaliacaoRepository.save(getAvaliacaoModel2());


    }

    @AfterEach
    void tearDown() {
        restauranteRepository.deleteAll();
        reservaRepository.deleteAll();
        avaliacaoRepository.deleteAll();
    }



    @Test
    void testaListarAvaliacaoPorRestauranteId(){

        int pagina = 0;
        int tamanho = 10;
        var restauranteId = "66c67aa035ed1f735450b7a2";


        var response = listarAvaliacaoPorRestauranteIdController.listarAvaliacaoPorRestauranteId(restauranteId, pagina, tamanho );


        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(0, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages())
        );

        assertAll("Teste Conteúdo",
                () -> assertEquals("99f89bb046ed2f846561b8b3", response.getBody().getContent().get(0).getId()),
                () -> assertEquals("66eb8772fd532626f457c740", response.getBody().getContent().get(0).getReservaId()),
                () -> assertEquals("Excelente serviço!", response.getBody().getContent().get(0).getComentario())
        );

    }

    @Test
    void testaListarAvaliacaoPorRestauranteIdPaginaInvalida(){

        int pagina = 1;
        int tamanho = 10;
        var restauranteId = "66c67aa035ed1f735450b7a2";


        var response = listarAvaliacaoPorRestauranteIdController.listarAvaliacaoPorRestauranteId(restauranteId, pagina, tamanho );


        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        assertAll("Teste metadata",
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(response.getBody().getMetadata()).size()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(response.getBody().getMetadata()).totalPages()),
                () -> assertEquals(0, response.getBody().getContent().size())
        );


    }


}
