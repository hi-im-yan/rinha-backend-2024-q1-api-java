package com.yanajiki.rinhabackendedicao2.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yanajiki.rinhabackendedicao2.dto.TransacaoDto;
import com.yanajiki.rinhabackendedicao2.model.Cliente;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Extrato {

    private final Saldo saldo;
    @JsonProperty("ultimas_transacoes")
    private List<TransacaoDto> ultimasTransacoes;

    public Extrato(final Cliente cliente) {
        this.saldo = new Saldo(cliente);
        this.ultimasTransacoes = cliente.buscasUltimasTransacoes(10).stream().map(TransacaoDto::new).collect(Collectors.toList());
    }
}
