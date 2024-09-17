package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AtualizarClienteAdapterTeste extends TesteConfig {

    @InjectMocks
    private AtualizarClienteAdapter atualizarClienteAdapter;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClientePresenter clientePresenter;

    @Test
    void testaAtualizarComSucesso(){
        var clienteModel = getClienteModel();

        when(clientePresenter.clienteEmClienteModel(any(Cliente.class))).thenReturn(clienteModel);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(clienteModel);
        when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(getClienteAtualizado());

        atualizarClienteAdapter.atualizarCliente(getClienteAtualizado());

        verify(clientePresenter, times(1)).clienteEmClienteModel(any(Cliente.class));
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
        verify(clientePresenter, times(1)).clienteModelEmCliente(any(ClienteModel.class));

    }

    @Test
    void testaBuscarPorIdComsucesso(){

        var clienteModel = getClienteModelAtualizado();
        var cliente = getClienteAtualizado();


        when(clienteRepository.findById(any(String.class))).thenReturn(Optional.of(clienteModel));
        when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(cliente);

        atualizarClienteAdapter.buscarPorId("66c67aa035ed1f735450b7a2");


        verify(clienteRepository, times(1)).findById(any(String.class));
        verify(clientePresenter, times(1)).clienteModelEmCliente(any(ClienteModel.class));

    }

    @Test
    void testaBuscarPorEmailComsucesso(){

        var clienteModel = getClienteModelAtualizado();
        var cliente = getClienteAtualizado();


        when(clienteRepository.findByEmail(any(String.class))).thenReturn(Optional.of(clienteModel));
        when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(cliente);

        atualizarClienteAdapter.buscarPorEmail("joaoatualizado@teste.com");


        verify(clienteRepository, times(1)).findByEmail(any(String.class));
        verify(clientePresenter, times(1)).clienteModelEmCliente(any(ClienteModel.class));

    }

}
