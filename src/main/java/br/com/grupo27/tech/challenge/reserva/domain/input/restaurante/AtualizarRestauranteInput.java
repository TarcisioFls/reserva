package br.com.grupo27.tech.challenge.reserva.domain.input.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarRestauranteInput {

    private String id;
    private String nome;
    private String descricao;
    private String localizacao;
    private String horaAbertura;
    private String horaFechamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private String proprietarioId;
}
