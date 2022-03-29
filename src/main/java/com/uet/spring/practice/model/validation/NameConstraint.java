package com.uet.spring.practice.model.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameConstraint {
    String message() default "Name of user is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
