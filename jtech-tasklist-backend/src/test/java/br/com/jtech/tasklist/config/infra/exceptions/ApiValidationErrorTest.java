package br.com.jtech.tasklist.config.infra.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiValidationErrorTest {

    @Test
    void shouldCreateWithAllFields() {
        ApiValidationError error = new ApiValidationError("User", "email", "invalid@email", "Invalid email format");

        assertEquals("User", error.getObject());
        assertEquals("email", error.getField());
        assertEquals("invalid@email", error.getRejectedValue());
        assertEquals("Invalid email format", error.getMessage());
    }

    @Test
    void shouldCreateWithObjectAndMessageOnly() {
        ApiValidationError error = new ApiValidationError("User", "Missing required field");

        assertEquals("User", error.getObject());
        assertEquals("Missing required field", error.getMessage());
        assertNull(error.getField());
        assertNull(error.getRejectedValue());
    }

    @Test
    void shouldRespectEqualsAndHashCode() {
        ApiValidationError error1 = new ApiValidationError("User", "email", "invalid@email", "Invalid email format");
        ApiValidationError error2 = new ApiValidationError("User", "email", "invalid@email", "Invalid email format");

        assertEquals(error1, error2);
        assertEquals(error1.hashCode(), error2.hashCode());
    }
}