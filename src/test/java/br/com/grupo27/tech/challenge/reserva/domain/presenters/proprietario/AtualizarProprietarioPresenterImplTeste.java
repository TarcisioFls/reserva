package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.AtualizarProprietarioDados.getAtualizarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AtualizarProprietarioPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private AtualizarProprietarioPresenterImpl atualizarProprietarioPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testaAtualizarProprietarioRequestEmAtualizarProprietarioInput() {

        var id = "66c67aa035ed1f735450b7a2";
        var atualizarProprietarioRequest = getAtualizarProprietarioRequest();
        var resultado = atualizarProprietarioPresenter.atualizarProprietarioRequestEmAtualizarProprietarioInput(id, atualizarProprietarioRequest);

        assertAll("teste",
                () -> assertEquals(id, resultado.getId()),
                () -> assertEquals("Maria", resultado.getNome()),
                () -> assertEquals("maria@teste.com", resultado.getEmail()),
                () -> assertEquals("abcd", resultado.getSenha()),
                () -> assertEquals("11988888888", resultado.getTelefone()),
                () -> assertEquals("11999999999", resultado.getCpf())
        );
    }

    @Test
    void testaAtualizarProprietarioInputEmProprietario() {

        var atualizarProprietarioInput = getAtualizarProprietarioInput();
        var proprietario = getProprietario();

        var resultado = atualizarProprietarioPresenter.atualizarProprietarioInputEmProprietario(proprietario, atualizarProprietarioInput);

        assertAll("teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("Maria", resultado.getNome()),
                () -> assertEquals("maria@teste.com", resultado.getEmail()),
                () -> assertEquals("abcd", resultado.getSenha()),
                () -> assertEquals("11988888888", resultado.getTelefone()),
                () -> assertEquals("11999999999", resultado.getCpf())
        );
    }

    @Test
    void testaProprietarioEmAtualizarProprietarioOutput() {

        var proprietario = getProprietario();
        var resultado = atualizarProprietarioPresenter.proprietarioEmAtualizarProprietarioOutput(proprietario);

        assertAll("teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("João", resultado.getNome()),
                () -> assertEquals("joao@teste.com", resultado.getEmail()),
                () -> assertEquals("123456", resultado.getSenha()),
                () -> assertEquals("11999999999", resultado.getTelefone()),
                () -> assertEquals("11999999999", resultado.getCpf())
        );
    }

    @Test
    void testaAtualizarProprietarioOutputEmProprietarioResponse() {

        var atualizarProprietarioOutput = getAtualizarProprietarioOutput();
        var resultado = atualizarProprietarioPresenter.atualizarProprietarioOutputEmProprietarioResponse(atualizarProprietarioOutput);

        assertAll("teste",
                () -> assertEquals("66c67aa035ed1f735450b7a2", resultado.getId()),
                () -> assertEquals("Maria", resultado.getNome()),
                () -> assertEquals("maria@teste.com", resultado.getEmail()),
                () -> assertEquals("abcd", resultado.getSenha()),
                () -> assertEquals("11988888888", resultado.getTelefone()),
                () -> assertEquals("11999999999", resultado.getCpf())
        );
    }

}