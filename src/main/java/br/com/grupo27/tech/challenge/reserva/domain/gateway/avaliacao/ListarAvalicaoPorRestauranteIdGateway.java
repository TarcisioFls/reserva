package br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import org.springframework.data.web.PagedModel;

import java.util.List;
import java.util.Optional;

public interface ListarAvalicaoPorRestauranteIdGateway {

  //  Optional<List<Avaliacao>> listarPorRestaurante(String restuaranteId);

    PagedModel<Avaliacao> listarPorRestaurante(String restuaranteId, int pagina, int tamanho);
}
