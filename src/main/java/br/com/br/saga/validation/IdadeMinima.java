package br.com.br.saga.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = IdadeMinimaValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IdadeMinima {
    String message() default "O usuário deve ter no mínimo 12 anos de idade.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
