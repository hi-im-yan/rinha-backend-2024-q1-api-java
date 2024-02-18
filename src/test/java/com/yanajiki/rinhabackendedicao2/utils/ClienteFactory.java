package com.yanajiki.rinhabackendedicao2.utils;

import com.yanajiki.rinhabackendedicao2.model.Cliente;

public class ClienteFactory {

    public static Cliente geraClienteCom10kDeLimiteESaldo0() {
        return new Cliente("JOAOZINHO", 10000, 0);
    }

    public static Cliente geraClienteCom10kDeLimiteESaldoNegativo10k() {
        return new Cliente("JOAOZINHO", 10000, -10000);
    }
}
