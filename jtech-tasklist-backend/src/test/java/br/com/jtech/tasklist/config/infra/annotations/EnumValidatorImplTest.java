package br.com.jtech.tasklist.config.infra.annotations;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EnumValidatorImplTest {

    private EnumValidatorImpl validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new EnumValidatorImpl();
        context = mock(ConstraintValidatorContext.class);

        EnumValidator annotation = new EnumValidator() {
            @Override
            public Class<? extends Enum<?>> enumClass() {
                return SampleStatus.class;
            }

            @Override
            public String message() {
                return "Invalid value";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends jakarta.validation.Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return EnumValidator.class;
            }
        };

        validator.initialize(annotation);
    }

    @Test
    void shouldReturnTrueForValidEnumValue() {
        assertTrue(validator.isValid("ACTIVE", context));
        assertTrue(validator.isValid("pending", context));
    }

    @Test
    void shouldReturnFalseForInvalidEnumValue() {
        assertFalse(validator.isValid("UNKNOWN", context));
        assertFalse(validator.isValid("actived", context));
    }

    @Test
    void shouldReturnTrueForNullValue() {
        assertTrue(validator.isValid(null, context));
    }

    enum SampleStatus {
        ACTIVE,
        INACTIVE,
        PENDING
    }
}
