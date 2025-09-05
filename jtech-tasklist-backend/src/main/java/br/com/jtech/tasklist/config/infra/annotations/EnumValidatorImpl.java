package br.com.jtech.tasklist.config.infra.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private EnumValidator annotation;

    @Override
    public void initialize(EnumValidator annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // Use @NotBlank separadamente se quiser obrigatoriedade

        return Arrays.stream(annotation.enumClass().getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}
