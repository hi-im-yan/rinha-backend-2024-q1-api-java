package com.yanajiki.rinhabackendedicao2.controller.response;

import com.yanajiki.rinhabackendedicao2.model.Cliente;
import lombok.Getter;

@Getter
public class ResultadoTransacao {

    private final int limite;
    private final int saldo;

    public ResultadoTransacao(Cliente cliente) {
        this.limite = cliente.getLimite();
        this.saldo = cliente.getSaldo();
    }
}
