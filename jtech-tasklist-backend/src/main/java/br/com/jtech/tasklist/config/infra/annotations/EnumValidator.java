package br.com.jtech.tasklist.config.infra.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value. Must match one of the enum values.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
