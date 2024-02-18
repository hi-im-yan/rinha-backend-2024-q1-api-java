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
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private Character tipo;

    @Column(nullable = false, length = 10)
    private String descricao;

    @Column(name = "realizada_em", insertable = false, updatable = false)
    private LocalDateTime realizadaEm;

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
