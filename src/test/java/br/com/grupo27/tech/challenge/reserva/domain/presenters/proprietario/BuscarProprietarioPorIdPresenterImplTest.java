package br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.mock.ProprietarioDados;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.BuscarProprietarioPorIdDados.buscarProprietarioPorIdOutput;
import static org.junit.jupiter.api.Assertions.assertAll;

class BuscarProprietarioPorIdPresenterImplTest extends TesteConfig {

    @InjectMocks
    private BuscarProprietarioPorIdPresenterImpl buscarProprietarioPorIdPresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeProprietarioEmBuscarProprietarioPorIdOutput() {

        var buscarProprietarioPorIdOutput = buscarProprietarioPorIdPresenter.proprietarioEmBuscarProprietarioPorIdOutput(ProprietarioDados.getProprietario());

        assertAll("buscarProprietarioPorIdOutput",
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", buscarProprietarioPorIdOutput.getId()),
                () -> Assertions.assertEquals("João", buscarProprietarioPorIdOutput.getNome()),
                () -> Assertions.assertEquals("joao@teste.com", buscarProprietarioPorIdOutput.getEmail()),
                () -> Assertions.assertEquals("123456", buscarProprietarioPorIdOutput.getSenha()),
                () -> Assertions.assertEquals("11999999999", buscarProprietarioPorIdOutput.getTelefone()),
                () -> Assertions.assertEquals("11999999999", buscarProprietarioPorIdOutput.getCpf())
        );
    }

    @Test
    void testeProprietarioResponseEmBuscarProprietarioPorIdOutput() {

        var buscarProprietarioPorIdOutput = buscarProprietarioPorIdPresenter.proprietarioResponseEmBuscarProprietarioPorIdOutput(buscarProprietarioPorIdOutput());

        assertAll("buscarProprietarioResponse",
                () -> Assertions.assertEquals("66c67aa035ed1f735450b7a2", buscarProprietarioPorIdOutput.getId()),
                () -> Assertions.assertEquals("João", buscarProprietarioPorIdOutput.getNome()),
                () -> Assertions.assertEquals("joao@teste.com", buscarProprietarioPorIdOutput.getEmail()),
                () -> Assertions.assertEquals("123456", buscarProprietarioPorIdOutput.getSenha()),
                () -> Assertions.assertEquals("11999999999", buscarProprietarioPorIdOutput.getTelefone()),
                () -> Assertions.assertEquals("11999999999", buscarProprietarioPorIdOutput.getCpf())
        );

    }
}