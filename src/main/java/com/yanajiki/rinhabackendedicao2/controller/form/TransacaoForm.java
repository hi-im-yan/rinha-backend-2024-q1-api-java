package com.yanajiki.rinhabackendedicao2.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransacaoForm {

    @NotNull(message = "informe o campo valor, tem que um número inteiro")
    private Integer valor;

    @NotBlank(message = "Informe o tipo da transacao -> 'c' ou 'd'")
    private Character tipo;

    @Size(min = 1, max = 10)
    private String descricao;
}
