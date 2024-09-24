package br.com.grupo27.tech.challenge.reserva.domain.presenters.avaliacao;

import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.request.CriarAvaliacaoRequest;
import br.com.grupo27.tech.challenge.reserva.application.controllers.avaliacao.response.AvaliacaoResponse;
import br.com.grupo27.tech.challenge.reserva.domain.entity.Avaliacao;
import br.com.grupo27.tech.challenge.reserva.domain.input.avaliacao.CriarAvaliacaoInput;
import br.com.grupo27.tech.challenge.reserva.domain.output.avaliacao.CriarAvaliacaoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarAvaliacaoPresenterImpl implements CriarAvaliacaoPresenter {

    private final ModelMapper mapper;

    @Override
    public Avaliacao criarAvaliacaoInputEmAvaliacao(CriarAvaliacaoInput input) {

        return new Avaliacao(input.getNota(), input.getComentario(), input.getReservaId());
    }

    @Override
    public CriarAvaliacaoOutput avaliacaoEmCriarAvaliacaoOutput(Avaliacao avaliacao) {

        return mapper.map(avaliacao, CriarAvaliacaoOutput.class);
    }

    @Override
    public CriarAvaliacaoInput criarAvaliacaoRequestEmCriarAvaliacaoInput(CriarAvaliacaoRequest request) {

        return mapper.map(request, CriarAvaliacaoInput.class);
    }

    @Override
    public AvaliacaoResponse criarAvaliacaoOutputEmAvaliacaoResponse(CriarAvaliacaoOutput output) {

        return mapper.map(output, AvaliacaoResponse.class);
    }
}
