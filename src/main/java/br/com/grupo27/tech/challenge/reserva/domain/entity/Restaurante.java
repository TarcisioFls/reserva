package br.com.grupo27.tech.challenge.reserva.domain.entity;

import br.com.grupo27.tech.challenge.reserva.domain.exception.ExceptionAdvice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.CAPACIDADE_INVALIDA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.DESCRICAO_OBRIGATORIA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.HORARIO_FUNCIONAMENTO_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.LOCALIZACAO_OBRIGATORIA;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.NOME_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.PROPRIETARIO_OBRIGATORIO;
import static br.com.grupo27.tech.challenge.reserva.domain.exception.CodigoError.TIPO_COZINHA_OBRIGATORIO;
import static java.util.Objects.isNull;

@Getter
@NoArgsConstructor
public class Restaurante {

    private String id;
    private String nome;
    private String descricao;
    private String localizacao;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;
    private int capacidade;
    private List<TipoCozinha> tipoCozinhaList;
    private Proprietario proprietario;

    public Restaurante(String nome,
                       String descricao,
                       String localizacao,
                       LocalTime horaAbertura,
                       LocalTime horaFechamento,
                       int capacidade,
                       List<TipoCozinha> tipoCozinhas,
                       Proprietario proprietario) {


        this.setNome(nome);
        this.setDescricao(descricao);
        this.setLocalizacao(localizacao);
        this.setHoraAbertura(horaAbertura);
        this.setHoraFechamento(horaFechamento);
        this.setCapacidade(capacidade);
        this.setTipoCozinhaList(tipoCozinhas);
        this.setProprietario(proprietario);
    }

    public Restaurante setId(String id) {
        this.id = id;
        return this;
    }

    public Restaurante setNome(String nome) {
        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_OBRIGATORIO);
        }
        this.nome = nome;
        return this;
    }

    public Restaurante setDescricao(String descricao) {
        if (isNull(descricao) || descricao.isBlank()) {
            throw new ExceptionAdvice(DESCRICAO_OBRIGATORIA);
        }
        this.descricao = descricao;
        return this;
    }

    public Restaurante setLocalizacao(String localizacao) {
        if (isNull(localizacao) || localizacao.isBlank()) {
            throw new ExceptionAdvice(LOCALIZACAO_OBRIGATORIA);
        }
        this.localizacao = localizacao;
        return this;
    }

    public Restaurante setHoraAbertura(LocalTime horaAbertura) {
        if (isNull(horaAbertura)) {
            throw new ExceptionAdvice(HORARIO_FUNCIONAMENTO_OBRIGATORIO);
        }
        this.horaAbertura = horaAbertura;
        return this;
    }

    public Restaurante setHoraFechamento(LocalTime horaFechamento) {
        if (isNull(horaFechamento)) {
            throw new ExceptionAdvice(HORARIO_FUNCIONAMENTO_OBRIGATORIO);
        }
        this.horaFechamento = horaFechamento;
        return this;
    }

    public Restaurante setCapacidade(int capacidade) {
        if (capacidade < 1) {
            throw new ExceptionAdvice(CAPACIDADE_INVALIDA);
        }
        this.capacidade = capacidade;
        return this;
    }

    public Restaurante setTipoCozinhaList(List<TipoCozinha> tipoCozinhaList) {
        if (isNull(tipoCozinhaList) || tipoCozinhaList.isEmpty()) {
            throw new ExceptionAdvice(TIPO_COZINHA_OBRIGATORIO);
        }
        this.tipoCozinhaList = tipoCozinhaList;
        return this;
    }

    public Restaurante setProprietario(Proprietario proprietario) {
        if (isNull(proprietario)) {
            throw new ExceptionAdvice(PROPRIETARIO_OBRIGATORIO);
        }
        this.proprietario = proprietario;
        return this;
    }
}
