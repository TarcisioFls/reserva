package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.ID_PROPRIETARIO_TESTE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testeNomeComSucesso() {
        assertDoesNotThrow(() -> restaurante.setNome("Restaurante Gourmet"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testeNomeComFalha(String nome) {
        assertExceptionMessage(() -> restaurante.setNome(nome), "Nome é obrigatório");
    }

    @Test
    void testeLocalizacaoComSucesso() {
        assertDoesNotThrow(() -> restaurante.setLocalizacao("123 Rua B"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testeLocalizacaoComFalha(String localizacao) {
        assertExceptionMessage(() -> restaurante.setLocalizacao(localizacao), "Localização é obrigatória");
    }

    @Test
    void testeDescricaoComSucesso() {
        assertDoesNotThrow(() -> restaurante.setDescricao("barato, mano, só vem!"));
    }

    @ParameterizedTest
    @MethodSource("nomesInvalidos")
    void testeDescricaoComFalha(String descricao) {
        assertExceptionMessage(() -> restaurante.setDescricao(descricao), "Descrição é obrigatória");
    }

    @Test
    void testeCapacidadeComSucesso() {
        assertDoesNotThrow(() -> restaurante.setCapacidade(300));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100000})
    void testeCapacidadeComFalha(int capacidade) {
        assertExceptionMessage(() -> restaurante.setCapacidade(capacidade), "Capacidade precisa ser maior que 0");
    }

    @Test
    void testeProprietarioComSucesso() {
        assertDoesNotThrow(() -> restaurante.setProprietarioId(ID_PROPRIETARIO_TESTE));
    }

    @Test
    void testeProprietarioComFalha() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setProprietarioId(null));
        assertEquals("Proprietário é obrigatório", exception.getMessage());
    }

    @Test
    void testeTipoCozinhaComSucesso() {
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();
        tipoCozinhaList.add(TipoCozinha.JAPONESA);
        assertDoesNotThrow(() -> restaurante.setTipoCozinhaList(tipoCozinhaList));
    }

    @Test
    void testeTipoCozinhaComFalha() {
        ExceptionAdvice exception;
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();

        exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setTipoCozinhaList(null));
        assertEquals("Tipo de Cozinha é obrigatório", exception.getMessage());

        exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setTipoCozinhaList(tipoCozinhaList));
        assertEquals("Tipo de Cozinha é obrigatório", exception.getMessage());
    }

    @Test
    void testaHorarioDeFuncionamentoComSucesso() {
        var horaAbertura = "07:00";
        var horaFechamento = "22:00";

        assertDoesNotThrow(() -> {
            restaurante.setHoraAbertura(horaAbertura);
            restaurante.setHoraFechamento(horaFechamento);
        });
    }

    @Test
    void testeHoraDeFechamentoNula() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraFechamento(null));
        assertEquals("Horário de Funcionamento é obrigatório", exception.getMessage());
    }

    @Test
    void testeHoraDeAberturaNula() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraAbertura(null));
        assertEquals("Horário de Funcionamento é obrigatório", exception.getMessage());
    }

    @Test
    void testaHoraAberturaInvalida() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraAbertura("25:00"));
        assertEquals("Horário de Funcionamento inválido", exception.getMessage());
    }

    @Test
    void testaHoraFechamentoInvalida() {
        var exception = assertThrows(ExceptionAdvice.class, () -> restaurante.setHoraFechamento("25:00"));
        assertEquals("Horário de Funcionamento inválido", exception.getMessage());
    }

    @Test
    void testaHoraAberturaMaiorQueHoraFechamento() {
        var exception = assertThrows(ExceptionAdvice.class, this::setInvalidOpeningAndClosingHours);
        assertEquals("Hora de abertura do restaurante não pode ser maior que a hora de fechamento", exception.getMessage());
    }

    private void assertExceptionMessage(Executable executavel, String mensagemEsperada) {
        var exception = assertThrows(ExceptionAdvice.class, executavel);
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    private void setInvalidOpeningAndClosingHours() {
        restaurante.setHoraAbertura("22:00");
        restaurante.setHoraFechamento("07:00");
    }

    static Stream<String> nomesInvalidos() {
        return Stream.of("", " ", "   ", null);
    }
}