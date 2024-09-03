package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.TodosProprietariosGateway;
import br.com.grupo27.tech.challenge.reserva.domain.presenters.proprietario.ProprietarioPresenter;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
public class TodosProprietariosAdapter implements TodosProprietariosGateway {

    private final ProprietarioRepository proprietarioRepository;
    private final ProprietarioPresenter proprietarioPresenter;

    @Override
    public Page<Proprietario> listarTodos(PageRequest paginacaoRequest) {

        var pageProprietarioModelList = proprietarioRepository.findAll(paginacaoRequest);

        return proprietarioPresenter.pageProprietarioModelListEmPageProprietarioList(pageProprietarioModelList);
    }

}
