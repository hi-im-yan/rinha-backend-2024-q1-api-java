package com.yanajiki.rinhabackendedicao2.utils;

import com.yanajiki.rinhabackendedicao2.model.Transacao;

public class TransacaoFactory {

    public static Transacao geraTransacaoDebitoDe1000() {
        return new Transacao(1000, 'd', "DESCRICAO");
    }

    public static Transacao geraTransacaoCreditoDe1000() {
        return new Transacao(1000, 'c', "DESCRICAO");
    }
}
