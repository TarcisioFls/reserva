package br.com.grupo27.tech.challenge.reserva.domain.output.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CriarRestauranteOutput extends RestauranteOutput{

    public CriarRestauranteOutput(String id, String nome, String descricao, String localizacao, String horaAbertura,
                                  String horaFechamento, int capacidade, List<TipoCozinha> tipoCozinhaList,
                                  String proprietarioId) {
        super(id, nome, descricao, localizacao, horaAbertura, horaFechamento, capacidade, tipoCozinhaList, proprietarioId);
    }

}
