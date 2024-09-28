package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getCriarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getProprieatarioResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.CriarProprietarioDados.getProprietarioDepoisDeSalvar;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CriarProprietarioPresenterImplTest extends TesteConfig {

    @InjectMocks
    private CriarProprietarioPresenterImpl criarProprietarioPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testaCriarProprietarioInputEmProprietario() {
        var criarProprietarioInput = getCriarProprietarioInput();
        var proprietarioEsperado = getProprietario();

        var resultado = criarProprietarioPresenter.criarProprietarioInputEmProprietario(criarProprietarioInput);

        assertEquals(proprietarioEsperado, resultado);
    }

    @Test
    void testaProprietarioEmCriarProprietarioOutput() {
        var proprietario = getProprietarioDepoisDeSalvar();
        var criarProprietarioOutputEsperado = getCriarProprietarioOutput();

        var resultado = criarProprietarioPresenter.proprietarioEmCriarProprietarioOutput(proprietario);

        assertEquals(criarProprietarioOutputEsperado, resultado);
    }

    @Test
    void testaCriarProprietarioEmCriarProprietarioInput() {
        var criarProprietarioRequest = getCriarProprietarioRequest();
        var criarProprietarioInputEsperado = getCriarProprietarioInput();

        var resultado = criarProprietarioPresenter.criarProprietarioEmCriarProprietarioInput(criarProprietarioRequest);

        assertEquals(criarProprietarioInputEsperado, resultado);
    }

    @Test
    void testaCriarProprietarioOutputEmProprietarioResponse() {
        var criarProprietarioOutput = getCriarProprietarioOutput();
        var proprietarioResponseEsperado = getProprieatarioResponse();

        var resultado = criarProprietarioPresenter.criarProprietarioOutputEmProprietarioResponse(criarProprietarioOutput);

        assertEquals(proprietarioResponseEsperado, resultado);
    }

}