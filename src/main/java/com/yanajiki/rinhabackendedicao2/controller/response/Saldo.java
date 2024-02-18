package com.yanajiki.rinhabackendedicao2.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yanajiki.rinhabackendedicao2.model.Cliente;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Saldo {

    private final int total;

    @JsonProperty("data_extrado")
    private final LocalDateTime dataExtrato;

    private final int limite;

    public Saldo(Cliente cliente) {
        this.total = cliente.getSaldo();
        this.dataExtrato = LocalDateTime.now();
        this.limite = cliente.getLimite();
    }
}
