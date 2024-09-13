package br.com.grupo27.tech.challenge.reserva.domain.useCase.restaurante;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.restaurante.AtualizarRestauranteGateway;
import br.com.grupo27.tech.challenge.reserva.domain.input.restaurante.AtualizarRestauranteInput;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.AtualizarRestaurantePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.RESTAURANTE_NAO_ENCONTRADO;
import static br.com.grupo27.tech.challenge.reserva.mock.restaurante.AtualizarRestauranteDados.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUserCaseTest extends TesteConfig {

    private AtualizarRestauranteInput input;

    @Mock
    private AtualizarRestauranteGateway atualizarRestauranteGateway;

    @Mock
    private AtualizarRestaurantePresenter atualizarRestaurantePresenter;

    @InjectMocks
    private AtualizarRestauranteUserCase atualizarRestauranteUserCase;

    @BeforeEach
    void inicia() {
        input = getAtualizarRestauranteInput();
    }

    @Test
    void testAtualizarComSucesso() {
        var restaurante = getRestaurante();
        var expectativaOutput = getAtualizarRestauranteOutput();
        var novoNome = "RIKIMARU TÁ ON";
        expectativaOutput.setNome(novoNome);


        when(atualizarRestauranteGateway.buscarPorId(input.getId())).thenReturn(Optional.of(restaurante));
        when(atualizarRestaurantePresenter.atualizarRestauranteInputEmRestaurante(input)).thenReturn(restaurante);
        when(atualizarRestauranteGateway.atualizar(restaurante)).thenReturn(restaurante);
        when(atualizarRestaurantePresenter.restauranteEmAtualizarRestauranteOutput(restaurante)).thenReturn(expectativaOutput);

        var resultado = atualizarRestauranteUserCase.atualizar(input);

        assertEquals(expectativaOutput, resultado);
        verify(atualizarRestauranteGateway, times(1)).buscarPorId(input.getId());
        verify(atualizarRestaurantePresenter, times(1)).atualizarRestauranteInputEmRestaurante(input);
        verify(atualizarRestauranteGateway, times(1)).atualizar(restaurante);
        verify(atualizarRestaurantePresenter, times(1)).restauranteEmAtualizarRestauranteOutput(restaurante);
    }

    @Test
    void testAtualizarRestauranteNaoEncontrado() {

        when(atualizarRestauranteGateway.buscarPorId(input.getId())).thenReturn(Optional.empty());

        var exception = assertThrows(ExceptionAdvice.class, () -> atualizarRestauranteUserCase.atualizar(input));

        assertEquals(RESTAURANTE_NAO_ENCONTRADO, exception.getCodigoError());
        verify(atualizarRestauranteGateway, times(1)).buscarPorId(input.getId());
        verify(atualizarRestaurantePresenter, never()).atualizarRestauranteInputEmRestaurante(any());
        verify(atualizarRestaurantePresenter, never()).restauranteEmAtualizarRestauranteOutput(any());
        verify(atualizarRestauranteGateway, never()).atualizar(any());
    }
}