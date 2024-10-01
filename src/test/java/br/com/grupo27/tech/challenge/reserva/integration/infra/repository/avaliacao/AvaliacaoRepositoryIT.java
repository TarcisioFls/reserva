package br.com.grupo27.tech.challenge.reserva.integration.infra.repository.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
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

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.getCriarAvaliacaoModelSemId;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class AvaliacaoRepositoryIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    void setUp() {
        avaliacaoRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        avaliacaoRepository.deleteAll();
    }

    @Test
    void testeSalvarAvaliacao() {
        var avaliacao = getCriarAvaliacaoModelSemId();

        avaliacaoRepository.save(avaliacao);
        var avaliacoes = avaliacaoRepository.findAll();

        assertEquals(1, avaliacoes.size());
        assertEquals(avaliacao, avaliacoes.getFirst());
    }
}
