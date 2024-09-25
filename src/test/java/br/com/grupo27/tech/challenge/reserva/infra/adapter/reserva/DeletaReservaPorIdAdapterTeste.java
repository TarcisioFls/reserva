package br.com.grupo27.tech.challenge.reserva.infra.adapter.reserva;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeletaReservaPorIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private DeletaReservaPorIdAdapter deletaReservaPorIdAdapter;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    void testaDelelataReservaPorId(){

         String id = "66eb8772fd532626f457c740";

         deletaReservaPorIdAdapter.deletaPorId(id);

         verify(reservaRepository, times(1)).deleteById(id);
    }
}
