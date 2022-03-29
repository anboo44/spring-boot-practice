package com.uet.spring.practice.model.validation;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringSecurityValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringSecurityConstraint {
    String message() default "Name of user contains restrict letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
