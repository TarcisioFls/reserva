package br.com.grupo27.tech.challenge.reserva.infra.adapter.cliente;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Cliente;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenterImpl;
import br.com.grupo27.tech.challenge.reserva.infra.model.ClienteModel;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getCliente;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CriarClienteTesteAdapterTeste extends TesteConfig {

    @InjectMocks
    private CriarClienteAdapter criarClienteAdapter;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClientePresenterImpl clientePresenter;

    @Test
    void testaCriar(){
        var clienteModel = getClienteModel();
        var cliente = getCliente();
        var clienteEsperado = getClienteDepoisDeSalvar();

        when(clientePresenter.clienteEmClienteModel(any(Cliente.class))).thenReturn(clienteModel);
        when(clienteRepository.save(any(ClienteModel.class))).thenReturn(clienteModel);
       when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(clienteEsperado);

        var resultado = criarClienteAdapter.criar(cliente);

        assertEquals(clienteEsperado, resultado);

        verify(clientePresenter, times(1)).clienteEmClienteModel(any(Cliente.class));
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
        verify(clientePresenter,times(1)).clienteModelEmCliente(any(ClienteModel.class));
    }

    @Test
    void testaBuscaPorEmailExistente(){
        var email = "joao@teste.com";
        var clienteModel = getClienteModel();
        var cliente = getCliente();

        when(clienteRepository.findByEmail(email)).thenReturn(Optional.of(clienteModel));
        when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(cliente);

        var resultado = criarClienteAdapter.buscaPorEmail(email);
        assertEquals(Optional.of(cliente), resultado);


    }

    @Test
    void testaBuscaPorEmailRetornaVazuiQuandoEmailNaoExiste(){
        var email = "joao333@teste.com";
        var clienteModel = getClienteModel();
        var cliente = getCliente();

        when(clienteRepository.findByEmail(email)).thenReturn(Optional.empty());


        var resultado = criarClienteAdapter.buscaPorEmail(email);
        assertEquals(Optional.empty(), resultado);


    }

    @Test
    void testaBuscaPorCpfExistente(){
        var cpf = "896.271.990-87";
        var clienteModel = getClienteModel();
        var cliente = getCliente();

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(clienteModel));
        when(clientePresenter.clienteModelEmCliente(any(ClienteModel.class))).thenReturn(cliente);

        var resultado = criarClienteAdapter.buscaPorCpf(cpf);
        assertEquals(Optional.of(cliente), resultado);


    }

    @Test
    void testaBuscaPorCpfRetornaVazioQuandoCpfNãoexiste(){
        var cpf = "803.339.270-36";



        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        var resultado = criarClienteAdapter.buscaPorCpf(cpf);
        assertEquals(Optional.empty(), resultado);


    }

    private static Cliente getClienteDepoisDeSalvar(){
        return getCliente();
    }
}
