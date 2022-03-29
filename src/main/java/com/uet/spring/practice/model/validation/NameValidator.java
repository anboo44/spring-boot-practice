package com.uet.spring.practice.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameConstraint, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return 4 < name.length() && name.length() < 15;
    }
}
