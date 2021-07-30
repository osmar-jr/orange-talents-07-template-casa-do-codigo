package br.com.zupacademy.osmarjunior.casadocodigo.annotation;

import br.com.zupacademy.osmarjunior.casadocodigo.validator.EstadoPertenceAoPaisValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EstadoPertenceAoPaisValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
@Documented
public @interface EstadoIsInPais {
    String message() default "Estado não pertence ao País informado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
