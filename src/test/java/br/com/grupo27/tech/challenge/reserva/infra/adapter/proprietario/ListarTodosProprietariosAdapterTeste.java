package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ListarTodosProprietariosAdapterTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosProprietariosAdapter listarTodosProprietariosAdapter;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private ProprietarioPresenter proprietarioPresenter;

    @Test
    void testeListarTodos() {
        var paginacaoRequest = PageRequest.of(0, 10);
        var pageProprietarioModel = ProprietarioDados.getPageProprietarioModel();

        when(proprietarioRepository.findAll(paginacaoRequest)).thenReturn(pageProprietarioModel);
        when(proprietarioPresenter.pageProprietarioModelListEmPageProprietarioList(pageProprietarioModel)).thenReturn(ProprietarioDados.getPageProprietario());

        var proprietarioPage = listarTodosProprietariosAdapter.listarTodos(paginacaoRequest);

        assertNotNull(proprietarioPage);

        assertAll("proprietarioPage",
                () -> assertEquals(2, proprietarioPage.getContent().size()),
                () -> assertEquals(0, requireNonNull(proprietarioPage.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(proprietarioPage.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(proprietarioPage.getMetadata()).totalElements())
        );

        assertAll("proprietarioPage.content",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(proprietarioPage.getContent().get(0)).getId()),
                () -> assertEquals("João", requireNonNull(proprietarioPage.getContent().get(0)).getNome()),
                () -> assertEquals("77b11aa035ed1f735459a1p0", requireNonNull(proprietarioPage.getContent().get(1)).getId()),
                () -> assertEquals("Maria", requireNonNull(proprietarioPage.getContent().get(1)).getNome())
        );

    }

}