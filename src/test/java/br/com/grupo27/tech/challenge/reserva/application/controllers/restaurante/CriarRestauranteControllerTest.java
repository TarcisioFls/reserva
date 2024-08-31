package br.com.grupo27.tech.challenge.reserva.application.controllers.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.presenters.restaurante.CriarRestaurantePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.CriarProprietarioUserCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteControllerTest {

    @InjectMocks
    private CriarRestauranteController criarRestauranteController;

    @Mock
    private CriarRestaurantePresenter criarRestaurantePresenter;

    @Mock
    private CriarProprietarioUserCase criarProprietarioUserCase;

    void testeCriar() {
    }
}