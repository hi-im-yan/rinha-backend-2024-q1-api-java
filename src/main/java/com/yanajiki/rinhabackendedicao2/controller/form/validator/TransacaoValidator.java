package com.yanajiki.rinhabackendedicao2.controller.form.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TransacaoValidator implements ConstraintValidator<TipoTransacao, Character> {

    @Override
    public void initialize(TipoTransacao constraintAnnotation) {
    }

    @Override
    public boolean isValid(Character value, ConstraintValidatorContext context) {
        return value != null && value.equals('c') || value.equals('d');
    }
}