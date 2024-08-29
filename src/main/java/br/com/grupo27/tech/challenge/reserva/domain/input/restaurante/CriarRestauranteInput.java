package br.com.grupo27.tech.challenge.reserva.domain.input.restaurante;

import br.com.grupo27.tech.challenge.reserva.domain.entity.Proprietario;
import br.com.grupo27.tech.challenge.reserva.domain.entity.TipoCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarRestauranteInput {

    private String nome;
    private String descricao;
    private String localizacao;
    private LocalDateTime horarioFuncionamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinha;
    private Proprietario proprietario;
}
