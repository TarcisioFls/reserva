package br.com.grupo27.tech.challenge.reserva.domain.output.proprietario;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodosProprietariosOutput {

    private long total;

    private List<Proprietario> conteudo;

    private Pageable pageable;

}
