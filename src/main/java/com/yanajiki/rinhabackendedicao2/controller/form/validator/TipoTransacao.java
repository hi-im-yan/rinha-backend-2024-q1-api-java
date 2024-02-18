package com.yanajiki.rinhabackendedicao2.controller.form.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransacaoValidator.class)
public @interface TipoTransacao {
    String message() default "Tipo de transação invalida. Deve ser 'c' ou 'd'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}