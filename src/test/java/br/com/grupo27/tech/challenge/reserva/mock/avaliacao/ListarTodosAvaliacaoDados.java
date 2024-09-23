package br.com.grupo27.tech.challenge.reserva.mock.avaliacao;

import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface ListarTodosAvaliacaoDados {

    String ID_AVALIACAO1 = "99f89bb046ed2f846561b8b3";
    String ID_AVALIACAO2 = "11G91Cc046ed2f846561b8c4";
    String ID_RESERVA1 = "11G91CC146fg2f846561b8c4";
    String ID_RESERVA2 = "22h12dd146fg2f846561c9d5";
    Integer NOTA1 = 5;
    Integer NOTA2 = 4;
    String COMENTARIO1 = "Excelente serviço!";
    String COMENTARIO2 = "Bom atendimento!";

    static Page<AvaliacaoModel> getPageAvaliacaoModel() {
        var avaliacao1 = new AvaliacaoModel(ID_AVALIACAO1, NOTA1, COMENTARIO1, ID_RESERVA1);
        var avaliacao2 = new AvaliacaoModel(ID_AVALIACAO2, NOTA2, COMENTARIO2, ID_RESERVA2);
        var todasAvaliacoesOutput = List.of(avaliacao1, avaliacao2);

        return new PageImpl<>(todasAvaliacoesOutput);
    }
}
