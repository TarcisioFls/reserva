package br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.application.factory.avaliacao.ListarAvaliacaoPorRestauranteIdUserCaseFactory;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.AvaliacaoPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao.ListarAvaliacaoPorRestauranteIdPresenter;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.reserva.ReservaPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.avaliacao.AvaliacaoRepository;
import br.com.grupo27.tech.challenge.reserva.infra.repository.reserva.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes/restauranteid")
public class ListarAvaliacaoPorRestauranteIdController {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ListarAvaliacaoPorRestauranteIdPresenter listarAvaliacaoPorRestauranteIdPresenter;
    private final AvaliacaoPresenter avaliacaoPresenter;
    private final ListarAvaliacaoPorRestauranteIdUserCaseFactory listarAvaliacaoPorRestauranteIdUserCaseFactory;
    private final ReservaRepository reservaRepository;
    private final ReservaPresenter reservaPresenter;

    @GetMapping("/{id}")
    public ResponseEntity<PagedModel<AvaliacaoResponse>> listarAvaliacaoPorRestauranteId(@PathVariable String id,
                                                                                         @RequestParam int pagina,
                                                                                         @RequestParam(defaultValue = "50") int tamanho){

        var listarAvaliacaoPorRestauranteIdUseCase = listarAvaliacaoPorRestauranteIdUserCaseFactory
                .buildListarAvaliacaoPorRestauranteIdUserCase(
                        listarAvaliacaoPorRestauranteIdPresenter,
                        avaliacaoPresenter,
                        avaliacaoRepository,
                        reservaPresenter,
                        reservaRepository
                );

        var listarAvaliacaoPorRestauranteIdOutPut = listarAvaliacaoPorRestauranteIdUseCase.listarPorRestauranteId(id, pagina, tamanho);

        var avaliacaoResponse = listarAvaliacaoPorRestauranteIdPresenter.pageAvaliacaoResponseEmListarAvaliacaoPorRestauranteIdOutput(listarAvaliacaoPorRestauranteIdOutPut);

        return ResponseEntity.ok(avaliacaoResponse);
    }


}
