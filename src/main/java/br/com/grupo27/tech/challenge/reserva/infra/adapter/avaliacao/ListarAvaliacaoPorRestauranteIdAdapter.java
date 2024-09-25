package br.com.grupo27.tech.challenge.reserva.infra.adapter.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao.ListarAvalicaoPorRestauranteIdGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ListarAvaliacaoPorRestauranteIdAdapter implements ListarAvalicaoPorRestauranteIdGateway {

    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoPresenter avaliacaoPresenter;

    @Override
    public Optional<List<Avaliacao>> listarPorRestaurante(String restauranteId) {

        var avaliacaoModelList = avaliacaoRepository.findByRestauranteId(restauranteId);
        return avaliacaoModelList.map(avaliacaoModels -> avaliacaoModels.stream()
                .map(avaliacaoPresenter::avaliacaoModelEmAvaliacao)
                .toList());
    }
}
