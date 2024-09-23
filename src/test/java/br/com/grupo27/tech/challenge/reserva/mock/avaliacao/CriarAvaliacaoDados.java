package br.com.grupo27.tech.challenge.reserva.mock.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request.CriarAvaliacaoRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao.CriarAvaliacaoInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.CriarAvaliacaoOutput;
import br.com.grupo27.tech.challenge.reserva.infra.model.AvaliacaoModel;

public interface CriarAvaliacaoDados {

    String ID_CRIAR_TESTE = "99f89bb046ed2f846561b8b3";
    String ID_CRIAR_RESERVA_TESTE = "11G91CC146fg2f846561b8c4";
    Integer NOTA = 4;
    String COMENTARIO = "Excelente serviço!";

    static CriarAvaliacaoRequest getCriarAvaliacaoRequest() {
        return criarRequest();
    }

    static CriarAvaliacaoInput getCriarAvaliacaoInput() {
        return criaInput();
    }

    static CriarAvaliacaoOutput getCriarAvaliacaoOutput() {
        return criarOutput();
    }

    static AvaliacaoResponse getAvaliacaoResponse() {
        return criarResponse();
    }

    static Avaliacao getCriarAvaliacao() {
        return criarAvaliacao();
    }

    static AvaliacaoModel getCriarAvaliacaoModel() {
        return criarAvaliacaoModel();
    }

    static AvaliacaoModel getCriarAvaliacaoModelSemId() {
        return criarAvaliacaoModelSemId();
    }

    private static AvaliacaoModel criarAvaliacaoModelSemId() {
        var avaliacaoModel = new AvaliacaoModel();
        avaliacaoModel.setNota(NOTA);
        avaliacaoModel.setComentario(COMENTARIO);
        avaliacaoModel.setReservaId(ID_CRIAR_RESERVA_TESTE);

        return avaliacaoModel;
    }

    private static AvaliacaoModel criarAvaliacaoModel() {
        return new AvaliacaoModel(ID_CRIAR_TESTE, NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }

    private static AvaliacaoResponse criarResponse() {
        return new AvaliacaoResponse(ID_CRIAR_TESTE, NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }

    private static CriarAvaliacaoOutput criarOutput() {
        return new CriarAvaliacaoOutput(ID_CRIAR_TESTE, NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }

    private static CriarAvaliacaoInput criaInput() {
        return new CriarAvaliacaoInput(NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }

    private static CriarAvaliacaoRequest criarRequest() {
        return new CriarAvaliacaoRequest(NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }

    private static Avaliacao criarAvaliacao() {
        return new Avaliacao(ID_CRIAR_TESTE, NOTA, COMENTARIO, ID_CRIAR_RESERVA_TESTE);
    }
}
