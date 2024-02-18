package com.yanajiki.rinhabackendedicao2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yanajiki.rinhabackendedicao2.model.Transacao;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransacaoDto {

    private final int valor;
    private final Character tipo;
    private final String descricao;

    @JsonProperty("realizada_em")
    private final LocalDateTime realizadaEm;

    public TransacaoDto(Transacao transacao) {
        this.valor = transacao.getValor();
        this.tipo = transacao.getTipo();
        this.descricao = transacao.getDescricao();
        this.realizadaEm = transacao.getRealizadaEm();
    }
}
