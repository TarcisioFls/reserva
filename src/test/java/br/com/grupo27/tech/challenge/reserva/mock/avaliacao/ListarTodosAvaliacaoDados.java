package br.com.grupo27.tech.challenge.reserva.mock.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.ListarTodosAvaliacaoOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ListarTodosAvaliacaoDados {

    String ID_AVALIACAO1 = "99f89bb046ed2f846561b8b3";
    String ID_RESERVA1 = "11G91CC146fg2f846561b8c4";
    Integer NOTA1 = 5;
    String COMENTARIO1 = "Excelente serviço!";

    String ID_AVALIACAO2 = "11G91Cc046ed2f846561b8c4";
    String ID_RESERVA2 = "22h12dd146fg2f846561c9d5";
    Integer NOTA2 = 4;
    String COMENTARIO2 = "Bom atendimento!";

    static Page<AvaliacaoModel> getPageAvaliacaoModel() {
        var avaliacao1 = new AvaliacaoModel(ID_AVALIACAO1, NOTA1, COMENTARIO1, ID_RESERVA1);
        var avaliacao2 = new AvaliacaoModel(ID_AVALIACAO2, NOTA2, COMENTARIO2, ID_RESERVA2);
        var todasAvaliacoesModel = List.of(avaliacao1, avaliacao2);

        return new PageImpl<>(todasAvaliacoesModel);
    }

    static PagedModel<Avaliacao> getPageAvaliacao() {
        var avaliacao1 = new Avaliacao(ID_AVALIACAO1, NOTA1, COMENTARIO1, ID_RESERVA1);
        var avaliacao2 = new Avaliacao(ID_AVALIACAO2, NOTA2, COMENTARIO2, ID_RESERVA2);
        var todasAvaliacoes = List.of(avaliacao1, avaliacao2);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodasAvaliacoes = new PageImpl<>(todasAvaliacoes, pageRequest, todasAvaliacoes.size());

        return new PagedModel<>(pageTodasAvaliacoes);
    }

    static PagedModel<AvaliacaoResponse> getPageAvaliacaoResponse() {
        var avaliacao1 = new AvaliacaoResponse(ID_AVALIACAO1, NOTA1, COMENTARIO1, ID_RESERVA1);
        var avaliacao2 = new AvaliacaoResponse(ID_AVALIACAO2, NOTA2, COMENTARIO2, ID_RESERVA2);
        var todasAvaliacoes = List.of(avaliacao1, avaliacao2);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodasAvaliacoes = new PageImpl<>(todasAvaliacoes, pageRequest, todasAvaliacoes.size());

        return new PagedModel<>(pageTodasAvaliacoes);
    }

    static PagedModel<ListarTodosAvaliacaoOutput> getPageAvaliacaoOutput() {
        var avaliacao1 = new ListarTodosAvaliacaoOutput(ID_AVALIACAO1, NOTA1, COMENTARIO1, ID_RESERVA1);
        var avaliacao2 = new ListarTodosAvaliacaoOutput(ID_AVALIACAO2, NOTA2, COMENTARIO2, ID_RESERVA2);
        var todasAvaliacoes = List.of(avaliacao1, avaliacao2);
        var pageRequest = PageRequest.of(0, 10);

        var pageTodasAvaliacoes = new PageImpl<>(todasAvaliacoes, pageRequest, todasAvaliacoes.size());

        return new PagedModel<>(pageTodasAvaliacoes);
    }
}
