package br.com.grupo27.tech.challenge.reserva.application.controllers.proprietario;

import br.com.grupo27.tech.challenge.reserva.application.factory.proprietario.DeletaProprietarioPorIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.proprietario.DeletaProprietarioPorIdUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeletaProprietarioPorIdController.class)
class DeletaProprietarioPorIdControllerTest extends TesteConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeletaProprietarioPorIdUserCaseFactory deletaProprietarioPorIdUserCaseFactory;

    @MockBean
    private DeletaProprietarioPorIdUserCase deletaProprietarioPorIdUserCase;

    @MockBean
    private ProprietarioRepository proprietarioRepository;

    @MockBean
    private ProprietarioPresenter proprietarioPresenter;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @Test
    void testeDeletePorId() throws Exception {

        var id = "66c67aa035ed1f735450b7a2";

        when(deletaProprietarioPorIdUserCaseFactory.buildDeletaProprietarioPorIdUserCase(
                proprietarioPresenter, proprietarioRepository, restauranteRepository)).thenReturn(deletaProprietarioPorIdUserCase);

        doNothing().when(deletaProprietarioPorIdUserCase).deletaPorId(id);

        mockMvc.perform(delete("/proprietarios/{id}", id))
                .andExpect(status().isNoContent());

        verify(deletaProprietarioPorIdUserCaseFactory, times(1)).buildDeletaProprietarioPorIdUserCase(
                proprietarioPresenter, proprietarioRepository, restauranteRepository);
        verify(deletaProprietarioPorIdUserCase, times(1)).deletaPorId(id);

    }

}