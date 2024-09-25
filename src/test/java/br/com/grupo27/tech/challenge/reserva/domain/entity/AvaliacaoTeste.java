package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.CriarAvaliacaoDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AvaliacaoTeste {

    private Avaliacao avaliacao;

    @BeforeEach
    void setUp() {
        avaliacao = new Avaliacao();
    }

    @Test
    void testeCriarAvaliacaoComSucesso() {
        avaliacao = new Avaliacao(ID_CRIAR_TESTE, NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);

        assertEquals(ID_CRIAR_TESTE, avaliacao.getId());
        assertEquals(NOTA, avaliacao.getNota());
        assertEquals(COMENTARIO, avaliacao.getComentario());
        assertEquals(ID_CRIAR_RESERVA_TESTE, avaliacao.getReservaId());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 6})
    void testeCriarAvalicaoComNotasInvalidas(int nota) {
        var exception = assertThrows(ExceptionAdvice.class, () -> avaliacao.setNota(nota));

        assertEquals("A nota da Avaliação só é permitido valores entre 0 e 5", exception.getMessage());
    }

    @Test
    void testeCriarAvaliacaoComNotaNull() {
        var exception =assertThrows(ExceptionAdvice.class, () -> avaliacao.setNota(null));

        assertEquals("A nota para a Avaliação é obrigatória", exception.getMessage());
    }
}