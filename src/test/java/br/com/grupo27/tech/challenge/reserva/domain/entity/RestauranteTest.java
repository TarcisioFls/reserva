package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.ID_PROPRIETARIO_TESTE;
import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testaNomeComSucesso() {
        assertDoesNotThrow(() -> restaurante.setNome("Restaurante Gourmet"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testaNomeComFalha(String nome) {
        assertExceptionMessage(() -> restaurante.setNome(nome), "Nome é obrigatório");
    }

    @Test
    void testaLocalizacaoComSucesso() {
        assertDoesNotThrow(() -> restaurante.setLocalizacao("123 Rua B"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testaLocalizacaoComFalha(String localizacao) {
        assertExceptionMessage(() -> restaurante.setLocalizacao(localizacao), "Localização é obrigatória");
    }

    @Test
    void testaDescricaoComSucesso() {
        assertDoesNotThrow(() -> restaurante.setDescricao("barato, mano, só vem!"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testaDescricaoComFalha(String descricao) {
        assertExceptionMessage(() -> restaurante.setDescricao(descricao), "Descrição é obrigatória");
    }

    @Test
    void testaCapacidadeComSucesso() {
        assertDoesNotThrow(() -> restaurante.setCapacidade(300));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100000})
    void testaCapacidadeComFalha(int capacidade) {
        assertExceptionMessage(() -> restaurante.setCapacidade(capacidade), "Capacidade precisa ser maior que 0");
    }

    @Test
    void testaProprietarioComSucesso() {
        assertDoesNotThrow(() -> restaurante.setProprietarioId(ID_PROPRIETARIO_TESTE));
    }

    @Test
    void testaProprietarioComFalha() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setProprietarioId(null));
        assertEquals("Proprietário é obrigatório", exception.getMessage());
    }

    @Test
    void testaTipoCozinhaComSucesso() {
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();
        tipoCozinhaList.add(TipoCozinha.JAPONESA);
        assertDoesNotThrow(() -> restaurante.setTipoCozinhaList(tipoCozinhaList));
    }

    @Test
    void testaTipoCozinhaComFalha() {
        ExceptionAdvice exception;
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();

        exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setTipoCozinhaList(null));
        assertEquals("Tipo de Cozinha é obrigatório", exception.getMessage());

        exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setTipoCozinhaList(tipoCozinhaList));
        assertEquals("Tipo de Cozinha é obrigatório", exception.getMessage());
    }

    @Test
    void testaHorarioDeFuncionamentoComSucesso() {
        var horaAbertura = LocalTime.of(7, 0);
        var horaFechamento = LocalTime.of(22, 0);

        assertDoesNotThrow(() -> {
            restaurante.setHoraAbertura(horaAbertura);
            restaurante.setHoraFechamento(horaFechamento);
        });
    }

    @Test
    void testaHoraDeFechamentoNula() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraFechamento(null));
        assertEquals("Horário de Funcionamento é obrigatório", exception.getMessage());
    }

    @Test
    void testaHoraDeAberturaNula() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraAbertura(null));
        assertEquals("Horário de Funcionamento é obrigatório", exception.getMessage());
    }

    private void assertExceptionMessage(Executable executavel, String mensagemEsperada) {
        var exception = assertThrows(ExceptionAdvice.class, executavel);
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    static Stream<String> nomesInvalidos() {
        return Stream.of("", " ", "   ", null);
    }
}