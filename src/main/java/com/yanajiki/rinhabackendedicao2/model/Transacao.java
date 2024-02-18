package com.yanajiki.rinhabackendedicao2.model;

import com.yanajiki.rinhabackendedicao2.controller.form.TransacaoForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transacoes")
@Getter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private final Integer valor;

    @Column(nullable = false)
    private final Character tipo;

    @Column(nullable = false, length = 10)
    private final String descricao;

    //realizada_em é controlada pelo banco de dados

    @ManyToOne
    @Setter
    private Cliente cliente;

    public Transacao(final Integer valor,
                     final Character tipo,
                     final String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Transacao(TransacaoForm transacaoForm) {
        this.valor = transacaoForm.getValor();
        this.tipo = transacaoForm.getTipo();
        this.descricao = transacaoForm.getDescricao();
    }
}
