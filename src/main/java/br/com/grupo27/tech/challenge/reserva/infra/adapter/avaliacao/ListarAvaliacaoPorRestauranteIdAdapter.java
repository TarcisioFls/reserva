package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError;
import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarAvalicaoPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListarAvaliacaoPorRestauranteIdAdapter implements ListarAvalicaoPorRestauranteIdGateway {

    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoPresenter avaliacaoPresenter;

    private final ReservaRepository reservaRepository;

    private final ReservaPresenter reservaPresenter;

    private ModelMapper mapper;

    @Override
    public PagedModel<Avaliacao> listarPorRestaurante(String restuaranteId, int pagina, int tamanho) {

        var reservaModel = reservaRepository.findByRestauranteId(restuaranteId).orElseThrow(
                ()-> new ExceptionAdvice(CodigoError.RESERVA_NAO_ENCONTRADA)
        );
        var reserva = reservaPresenter.reservaModelEmReserva(reservaModel);
        var avaliacaoModelList = avaliacaoRepository.findByReservaId(reserva.getId(), PageRequest.of(pagina, tamanho));



        return avaliacaoPresenter.pageAvaliacaoModelListEmPageAvaliacaoList(avaliacaoModelList);

    }
}
