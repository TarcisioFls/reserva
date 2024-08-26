package br.com.grupo27.tech.challenge.reserva.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    void testa_set_nome_com_sucesso() {
        assertDoesNotThrow(() -> restaurante.setNome("Restaurante Gourmet"));
    }

    @Test
    void testa_set_nome_com_falha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(" "));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setNome(""));
    }

    @Test
    void testa_set_localizacao_com_sucesso() {
        assertDoesNotThrow(() -> restaurante.setLocalizacao("123 Rua B"));
    }

    @Test
    void testa_set_localizacao_com_falha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setLocalizacao(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setLocalizacao(""));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setLocalizacao(" "));
    }

    @Test
    void testa_set_descricao_com_sucesso() {
        assertDoesNotThrow(() -> restaurante.setDescricao("barato, mano, só vem!"));
    }

    @Test
    void testa_set_descricao_com_falha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setDescricao(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setDescricao(""));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setDescricao(" "));
    }

    @Test
    void testa_set_capacidade_com_sucesso() {
        assertDoesNotThrow(() -> restaurante.setCapacidade(300));

    }

    @Test
    void testa_set_capacidade_com_falha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setCapacidade(0));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setCapacidade(-1));
    }

    @Test
    void testa_set_proprietario_com_sucesso() {
        Proprietario proprietario = new Proprietario();
        assertDoesNotThrow(() -> restaurante.setProprietario(proprietario));
    }

    @Test
    void testa_set_proprietario_com_falha() {
        assertThrows(IllegalArgumentException.class, () -> restaurante.setProprietario(null));
    }

    @Test
    void testa_set_tipoCozinha_com_sucesso() {
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();
        tipoCozinhaList.add(TipoCozinha.JAPONESA);
        assertDoesNotThrow(() -> restaurante.setTipoCozinhaList(tipoCozinhaList));
    }

    @Test
    void testa_set_tipoCozinha_com_falha() {
        List<TipoCozinha> tipoCozinhaList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> restaurante.setTipoCozinhaList(null));
        assertThrows(IllegalArgumentException.class, () -> restaurante.setTipoCozinhaList(tipoCozinhaList));
    }
}