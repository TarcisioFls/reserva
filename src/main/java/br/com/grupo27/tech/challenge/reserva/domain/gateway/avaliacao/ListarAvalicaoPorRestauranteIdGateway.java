package br.com.grupo27.tech.challenge.reserva.domain.gateway.avaliacao;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import org.springframework.data.web.PagedModel;

public interface ListarAvalicaoPorRestaurante {

    PagedModel<Avaliacao> listarPorRestaurante(String idRestaurante);
}
