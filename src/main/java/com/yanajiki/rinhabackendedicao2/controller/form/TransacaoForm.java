package com.yanajiki.rinhabackendedicao2.controller.form;

import com.yanajiki.rinhabackendedicao2.controller.form.validator.TipoTransacao;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransacaoForm {

//    @Min(value = 1, message = "informe um valor para transacao")
    @Digits(integer = 100000000, fraction = 0)
    private BigDecimal valor;

    @TipoTransacao
    private Character tipo;

    @Size(min = 1, max = 10, message = "descricao deve conter de 1 a 10 characteres")
    private String descricao;

    public Integer getValor() {
        return valor.intValue();
    }
}
