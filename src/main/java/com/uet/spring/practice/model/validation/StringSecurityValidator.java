package com.uet.spring.practice.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringSecurityValidator implements ConstraintValidator<StringSecurityConstraint, String> {

    public StringSecurityValidator() {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.contains("#");
    }
}
