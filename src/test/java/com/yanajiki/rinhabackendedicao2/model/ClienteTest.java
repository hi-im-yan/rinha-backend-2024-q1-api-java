package com.yanajiki.rinhabackendedicao2.model;

import com.yanajiki.rinhabackendedicao2.exception.TransacaoNaoProcessavelException;
import com.yanajiki.rinhabackendedicao2.utils.ClienteFactory;
import com.yanajiki.rinhabackendedicao2.utils.TransacaoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {


    @Test
    @DisplayName("Deve descontar do saldo se tipo da transação for DEBITO e se tiver dentro do limite")
    void testDescontarSaldoParaDebito() {
        Cliente cliente = ClienteFactory.geraClienteCom10kDeLimiteESaldo0();

        assertDoesNotThrow(() -> cliente.processaTransacao(TransacaoFactory.geraTransacaoDebitoDe1000()));
    }

    @Test
    @DisplayName("Deve descontar do saldo se tipo da transação for CREDITO")
    void testDescontarSaldoParaCredito() {
        Cliente cliente = ClienteFactory.geraClienteCom10kDeLimiteESaldo0();

        assertDoesNotThrow(() -> cliente.processaTransacao(TransacaoFactory.geraTransacaoCreditoDe1000()));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o tipo de transação for DEBITO e extrapolar o limite")
    void testLancarExcecaoAoTentarDescontarAlemDoLimiteQuandoTransacaoDebito() {
        Cliente cliente = ClienteFactory.geraClienteCom10kDeLimiteESaldoNegativo10k();

        assertThrows(TransacaoNaoProcessavelException.class, () -> cliente.processaTransacao(TransacaoFactory.geraTransacaoDebitoDe1000()));
    }

}