package com.yanajiki.rinhabackendedicao2.controller.form;

import com.yanajiki.rinhabackendedicao2.controller.form.validator.TipoTransacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransacaoForm {

    @Min(value = 1, message = "informe um valor para transacao")
    private Integer valor;

    @TipoTransacao
    private Character tipo;

    @Size(min = 1, max = 10, message = "descricao deve conter de 1 a 10 characteres")
    private String descricao;
}
