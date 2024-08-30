package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.*;
import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class Restaurante {

    private String id;
    private String nome;
    private String descricao;
    private String localizacao;
    private LocalDateTime horarioFuncionamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private Proprietario proprietario;

    public Restaurante(String nome,
                       String descricao,
                       String localizacao,
                       LocalDateTime horarioFuncionamento,
                       Integer capacidade,
                       List<TipoCozinha> tipoCozinhas,
                       Proprietario proprietario) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }

        if (isNull(descricao) || descricao.isBlank()) {
            throw new ExceptionAdvice(DESCRICAO_OBRIGATORIA);
        }

        if (isNull(localizacao) || localizacao.isBlank()) {
            throw new ExceptionAdvice(LOCALIZACAO_OBRIGATORIA);
        }

        if (capacidade < 1) {
            throw new ExceptionAdvice(CAPACIDADE_INVALIDA);
        }

        if (isNull(tipoCozinhas) || tipoCozinhas.isEmpty()) {
            throw new ExceptionAdvice(TIPO_COZINHA_OBRIGATORIA);
        }

        if (isNull(proprietario)) {
            throw new ExceptionAdvice(PROPRIETARIO_OBRIGATORIO);
        }

        this.setNome(nome);
        this.setDescricao(descricao);
        this.setLocalizacao(localizacao);
        this.setHorarioFuncionamento(horarioFuncionamento);
        this.setCapacidade(capacidade);
        this.setTipoCozinhaList(tipoCozinhas);
        this.setProprietario(proprietario);
    }
}
