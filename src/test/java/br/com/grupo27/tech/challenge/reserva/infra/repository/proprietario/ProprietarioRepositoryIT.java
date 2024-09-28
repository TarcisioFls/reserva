package br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario;

import br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class ProprietarioRepositoryIT {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @BeforeEach
    void setUp() {
        proprietarioRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        proprietarioRepository.deleteAll();
    }

    @Test
    void testeSalvarProprietario() {
        var proprietario = ProprietarioDados.getProprietarioModelSemId();

        proprietarioRepository.save(proprietario);
        var proprietarios = proprietarioRepository.findAll();

        assertEquals(1, proprietarios.size());
        assertEquals(proprietario, proprietarios.get(0));
    }
}