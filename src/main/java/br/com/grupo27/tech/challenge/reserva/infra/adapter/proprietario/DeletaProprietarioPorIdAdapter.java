package br.com.grupo27.tech.challenge.reserva.infra.adapter.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.gateway.proprietario.DeletaProprietarioPorIdGateway;
import br.com.grupo27.tech.challenge.reserva.infra.repository.proprietario.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletaProprietarioPorIdAdapter implements DeletaProprietarioPorIdGateway {

    private final ProprietarioRepository proprietarioRepository;

    @Override
    public void deletaPorId(String id) {

        proprietarioRepository.deleteById(id);
    }
}
