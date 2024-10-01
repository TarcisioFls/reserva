package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import br.com.grupo27.tech.challenge.reserva.mock.reserva.ReservaDados;
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

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.COMENTARIO;
import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoRequest;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class CriarAvaliacaoControllerIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private CriarAvaliacaoController criarAvaliacaoController;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        avaliacaoRepository.deleteAll();

        var reserva = ReservaDados.getReservaModel();
        reserva.setId("11G91CC146fg2f846561b8c4");
        reservaRepository.save(reserva);
    }

    @AfterEach
    void tearDown() {
        avaliacaoRepository.deleteAll();
    }

    @Test
    void testeCriarAvaliacao() {
        var criarAvaliacaoRequest = getCriarAvaliacaoRequest();

        var avaliacaoResponse = criarAvaliacaoController.criar(criarAvaliacaoRequest);

        assertEquals(200, avaliacaoResponse.getStatusCode().value());
        assertTrue(nonNull(avaliacaoResponse.getBody()));
        assertEquals(COMENTARIO, avaliacaoResponse.getBody().getComentario());
    }
}
