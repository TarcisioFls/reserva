package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.CriarProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.CriarProprietarioUserCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioInput;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioOutput;
import static br.com.grupo27.tech.challenge.reserva.mock.CriarProprietarioDados.getCriarProprietarioRequest;
import static br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados.getProprietarioResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarProprietarioControllerTeste {

    @InjectMocks
    private CriarProprietarioController criarProprietarioController;

    @Mock
    private CriarProprietarioPresenter criarProprietarioPresenter;

    @Mock
    private CriarProprietarioUserCase criarProprietarioUserCase;

    void testeCriar() {
        var criarProprietarioRequest = getCriarProprietarioRequest();
        var criarProprietarioInput = getCriarProprietarioInput();
        var criarProprietarioOutput = getCriarProprietarioOutput();
        var proprietarioResponse = getProprietarioResponse();

        when(criarProprietarioPresenter.criarProprietarioEmCriarProprietarioInput(criarProprietarioRequest)).thenReturn(criarProprietarioInput);
        when(criarProprietarioUserCase.criar(criarProprietarioInput)).thenReturn(criarProprietarioOutput);
        when(criarProprietarioPresenter.criarProprietarioOutputEmProprietarioResponse(criarProprietarioOutput)).thenReturn(proprietarioResponse);

        var resultado = criarProprietarioController.criar(criarProprietarioRequest);

        assertEquals(proprietarioResponse, resultado.getBody());
    }

}