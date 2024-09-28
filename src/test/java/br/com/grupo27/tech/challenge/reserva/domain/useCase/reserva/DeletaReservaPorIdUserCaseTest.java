package br.com.grupo27.tech.challenge.reserva.domain.useCase.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.reserva.DeletaReservaPorIdGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeletaReservaPorIdUserCaseTest extends TesteConfig {

    @InjectMocks
    private DeletaReservaPorIdUserCase deletaReservaPorIdUserCase;

    @Mock
    private DeletaReservaPorIdGateway deletaReservaPorIdGateway;

    @Test
    void testaDeletePorId(){

        var id = "66eb8772fd532626f457c740";

        deletaReservaPorIdUserCase.deletaPorId(id);

        verify(deletaReservaPorIdGateway,times(1)).deletaPorId(id);
    }


}
