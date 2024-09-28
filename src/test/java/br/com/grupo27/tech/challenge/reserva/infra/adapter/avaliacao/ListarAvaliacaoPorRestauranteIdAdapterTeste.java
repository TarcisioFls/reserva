package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.config.TesteConfig;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static br.com.grupo27.tech.challenge.reserva.mock.avaliacao.ListarAvaliacaoPorRestauranteIdDados.*;
import static br.com.grupo27.tech.challenge.reserva.mock.reserva.CriarReservaDados.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ListarAvaliacaoPorRestauranteIdAdapterTeste extends TesteConfig {

    @InjectMocks
    private ListarAvaliacaoPorRestauranteIdAdapter listarAvaliacaoPorRestauranteIdAdapter;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private AvaliacaoPresenter avaliacaoPresenter;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ReservaPresenter reservaPresenter;



    @Test
    void testaListarAvaliacaoPorRestauranteId(){

        var pagina = 0;
        var tamanho = 10;
        var restaurandeId ="66c67aa035ed1f735450b7a2";
        var reservaId = "66eb8772fd532626f457c740";
        var pageAvaliacaoModel = getPageAvaliacaoModel();
        var optionalReservaModelList = Optional.of(getReservaModelAvaliacao());
        var pageAvaliacao = getPageAvaliacao();

        when(reservaRepository.findByRestauranteId(restaurandeId)).thenReturn(optionalReservaModelList);
        when(reservaPresenter.reservaModelEmReserva(any())).thenReturn(getReservaAvaliacao());
        when(avaliacaoRepository.findByReservaId(getReservaModelAvaliacao().getRestauranteId(),PageRequest.of(pagina, tamanho))).thenReturn(pageAvaliacaoModel);
        when(avaliacaoPresenter.pageAvaliacaoModelListEmPageAvaliacaoList(pageAvaliacaoModel)).thenReturn(pageAvaliacao);

        listarAvaliacaoPorRestauranteIdAdapter.listarPorRestaurante(restaurandeId, pagina, tamanho);

        verify(reservaRepository, times(1)).findByRestauranteId(restaurandeId);
        verify(reservaPresenter, times(1)).reservaModelEmReserva(any());
        verify(avaliacaoRepository, times(1)).findByReservaId(getReservaModelAvaliacao().getRestauranteId(), PageRequest.of(pagina, tamanho) );
        verify(avaliacaoPresenter, times(1)).pageAvaliacaoModelListEmPageAvaliacaoList((pageAvaliacaoModel));


    }
}
