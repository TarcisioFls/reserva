package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ListarTodosProprietariosDados.getPageProprietario;
import static br.com.grupo27.tech.challenge.reserva.mock.proprietario.ListarTodosProprietariosDados.getPageTodosProprietariosOutput;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListarTodosProprietariosPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosProprietariosPresenterImpl listarTodosProprietariosPresenterImpl;

    @Spy
    private ModelMapper mapper;

    @Test
    void testaPageProprietarioEmPageTodosProprietariosOutput() {

        var resultado = listarTodosProprietariosPresenterImpl.pageProprietarioEmPageTodosProprietariosOutput(getPageProprietario());

        assertNotNull(resultado);
        assertAll("Teste Paginaçõa",
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste ListarTodosProprietariosOutput",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0).getId())),
                () -> assertEquals("João", requireNonNull(resultado.getContent().get(0).getNome())),
                () -> assertEquals("77b11aa035ed1f735459a1p0", requireNonNull(resultado.getContent().get(1).getId())),
                () -> assertEquals("Maria", requireNonNull(resultado.getContent().get(1).getNome()))
        );
    }

    @Test
    void testePageTodosProprietariosOutputEmPageProprietarioListResponse() {

        var resultado = listarTodosProprietariosPresenterImpl.pageTodosProprietariosOutputEmPageProprietarioListResponse(getPageTodosProprietariosOutput());

        assertNotNull(resultado);
        assertAll("Teste Paginaçõa",
                () -> assertEquals(2, requireNonNull(resultado.getContent()).size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste ProprietarioResponse",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0).getId())),
                () -> assertEquals("João", requireNonNull(resultado.getContent().get(0).getNome())),
                () -> assertEquals("77b11aa035ed1f735459a1p0", requireNonNull(resultado.getContent().get(1).getId())),
                () -> assertEquals("Maria", requireNonNull(resultado.getContent().get(1).getNome()))
        );
    }

}