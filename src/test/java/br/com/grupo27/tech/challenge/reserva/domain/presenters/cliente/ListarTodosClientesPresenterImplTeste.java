package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ListarTodosClientesDados.*;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListarTodosClientesPresenterImplTeste extends TesteConfig {

    @InjectMocks
    private ListarTodosClientesPresenterImpl listarTodosClientesPresenterImpl;

    @Spy
    private ModelMapper mapper;

    @Test
    void testaPageClienteEmPageTodosClientesOutput(){

        var resultado = listarTodosClientesPresenterImpl.pageClienteEmPageTodosClienteOutput(getPageCliente());

        assertNotNull(resultado);

        assertAll("Teste Paginacão",
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements())
        );

        assertAll("Teste ListarTodosProprietariosOutput",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0)).getId()),
                () -> assertEquals("João Rodrigo", requireNonNull(resultado.getContent().get(0)).getNome()),
                () -> assertEquals("66c67aa035ed1f735450b7a1", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("João atualizado", requireNonNull(resultado.getContent().get(1)).getNome())
        );
    }

    @Test
    void testaPageTodosClientesOutputEmPageClienteListResponse(){

        var resultado = listarTodosClientesPresenterImpl.pageTodosClienteOutputEmPageClienteListResponse(getPageTodosClientesOutput());

        assertNotNull(resultado);

        assertAll("Teste Paginacão",
                () -> assertEquals(2, resultado.getContent().size()),
                () -> assertEquals(0, requireNonNull(resultado.getMetadata()).number()),
                () -> assertEquals(10, requireNonNull(resultado.getMetadata()).size()),
                () -> assertEquals(2, requireNonNull(resultado.getMetadata()).totalElements()),
                () -> assertEquals(1, requireNonNull(resultado.getMetadata()).totalPages())
        );

        assertAll("Teste ProprietarioResponse",
                () -> assertEquals("66c67aa035ed1f735450b7a2", requireNonNull(resultado.getContent().get(0)).getId()),
                () -> assertEquals("João Rodrigo", requireNonNull(resultado.getContent().get(0)).getNome()),
                () -> assertEquals("66c67aa035ed1f735450b7a1", requireNonNull(resultado.getContent().get(1)).getId()),
                () -> assertEquals("João atualizado", requireNonNull(resultado.getContent().get(1)).getNome())
        );

    }

}
