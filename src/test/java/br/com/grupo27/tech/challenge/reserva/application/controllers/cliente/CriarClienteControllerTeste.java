package br.com.grupo27.tech.challenge.reserva.application.controllers.cliente;


import br.com.grupo27.tech.challenge.reserva.application.controllers.Cliente.CriarClienteController;
import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.ClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.cliente.CriarClientePresenter;
import br.com.grupo27.tech.challenge.reserva.domain.useCase.cliente.CriarClienteUserCase;
import br.com.grupo27.tech.challenge.reserva.infra.repository.cliente.ClienteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import static br.com.grupo27.tech.challenge.reserva.mock.cliente.ClienteDados.getClienteResponse;
import static br.com.grupo27.tech.challenge.reserva.mock.cliente.CriarClienteDados.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CriarClienteControllerTeste  extends TesteConfig {

   // private MockMvc mockMvc;

    @InjectMocks
    private CriarClienteController criarClienteController;

    @Mock
    private CriarClienteUserCase criarClienteUserCase;
    @Mock
    private  CriarClientePresenter criarClientePresenter;
    @Mock
    private  ClientePresenter clientePresenter;
    @Mock
    private ClienteRepository clienteRepository;


//    AutoCloseable mock;
//    @BeforeEach
//    void setUp() {
//        mock = MockitoAnnotations.openMocks(this);
//        ClienteResponseController clienteResponseController = new ClienteResponseController(clienteRepository, criarClientePresenter, clientePresenter);
//        mockMvc = MockMvcBuilders.standaloneSetup(clienteResponseController)
//                .build();
//
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        mock.close();
//    }


    void testeCriar() throws Exception {
        var request = getCriarClienteRequest();
        var criarClienteInput =  getCriarClienteInput();
        var criarClienteOutput = getCriarClienteOutput();
        var clienteResponse = getClienteResponse();

        when(criarClientePresenter.criarClienteEmClienteInput(request)).thenReturn(criarClienteInput);
        when(criarClienteUserCase.criar(criarClienteInput)).thenReturn(criarClienteOutput);
        when(criarClientePresenter.criarClienteOutputEmClienteResponse(criarClienteOutput)).thenReturn(clienteResponse);

        var resultado = criarClienteController.criarCliente(request);

        assertEquals(clienteResponse, resultado.getBody());

      //  mockMvc.perform(post("/clientes", request)).andExpect(status().isOk());

    }





}
