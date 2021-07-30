package br.com.zupacademy.osmarjunior.casadocodigo.annotation;


import br.com.zupacademy.osmarjunior.casadocodigo.validator.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = UniqueValueValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface UniqueValue {
    String message() default "Value entered already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> classDomain();
    String attributeName();
}
