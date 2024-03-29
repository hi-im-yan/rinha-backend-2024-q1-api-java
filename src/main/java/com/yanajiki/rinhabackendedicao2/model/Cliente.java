package com.yanajiki.rinhabackendedicao2.model;

import com.yanajiki.rinhabackendedicao2.exception.TransacaoNaoProcessavelException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private int limite;

    @Column
    private int saldo;

    @OneToMany(mappedBy = "cliente")
    private List<Transacao> transacoes;

    private static final Character DEBITO = 'd';
    private static final Character CREDITO = 'c';

    public Cliente(String nome, int limite, int saldo) {
        this.nome = nome;
        this.limite = limite;
        this.saldo = saldo;
    }

    public void processaTransacao(final Transacao transacao) {
        if (transacao.getTipo().equals(DEBITO) && !transacaoDebitoProcessavel(transacao.getValor())) {
            throw new TransacaoNaoProcessavelException();
        }

        this.saldo -= transacao.getValor();
        transacao.setCliente(this);
    }

    public List<Transacao> buscasUltimasTransacoes(final int tamanhoHistorico) {
        return this.transacoes.subList(Math.max(this.transacoes.size() - tamanhoHistorico, 0), this.transacoes.size());
    }

    private boolean transacaoDebitoProcessavel(final Integer valorTransacao) {
        int saldoFuturo = this.saldo - valorTransacao;
        return saldoFuturo * -1 <= this.limite;
    }
}
