package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ProprietarioDados.getProprietarioModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProprietarioPresenterImplTest extends TesteConfig {

    @InjectMocks
    private ProprietarioPresenterImpl proprietarioPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeProprietarioEmProprietarioModel() {
        var proprietario = getProprietario();
        var proprietarioModelEsperado = getProprietarioModel();

        var resultado = proprietarioPresenter.proprietarioEmProprietarioModel(proprietario);

        assertEquals(proprietarioModelEsperado, resultado);
    }

    @Test
    void testeProprietarioModelEmProprietario() {
        var proprietarioModel = getProprietarioModel();
        var proprietarioEsperado = getProprietario();

        var resultado = proprietarioPresenter.proprietarioModelEmProprietario(proprietarioModel);

        assertEquals(proprietarioEsperado, resultado);
    }

}