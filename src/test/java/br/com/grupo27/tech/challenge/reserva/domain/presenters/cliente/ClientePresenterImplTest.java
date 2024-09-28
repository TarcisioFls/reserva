package br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientePresenterImplTest extends TesteConfig {

    @InjectMocks
    private ClientePresenterImpl clientePresenter;

    @Spy
    private ModelMapper mapper;

    @Test
    void testeClienteEmClienteModel(){
        var cliente = getCliente();
        var clienteModelEsperado = getClienteModel();

        var resultado = clientePresenter.clienteEmClienteModel(cliente);

        assertEquals(clienteModelEsperado, resultado);
    }

    @Test
    void testeClienteModelEmCliente(){

        var clienteModel = getClienteModel();
        var clienteEsperado = getCliente();

        var resultado = clientePresenter.clienteModelEmCliente(clienteModel);

        assertEquals(clienteEsperado, resultado);
    }
}
