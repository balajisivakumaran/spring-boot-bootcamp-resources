package com.ltp.javagram;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface UserValidation {

    String message() default "Last name cannot be the same as first name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

