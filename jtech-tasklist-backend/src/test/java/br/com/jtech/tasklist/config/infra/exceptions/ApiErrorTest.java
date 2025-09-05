package br.com.jtech.tasklist.config.infra.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorTest {

    @Test
    void shouldCreateApiErrorWithStatusOnly() {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);

        assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
        assertNotNull(error.getTimestamp());
        assertNull(error.getMessage());
        assertNull(error.getDebugMessage());
        assertNull(error.getSubErrors());
    }

    @Test
    void shouldCreateApiErrorWithStatusAndException() {
        Exception ex = new RuntimeException("Something went wrong");
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getStatus());
        assertEquals("Unexpected error", error.getMessage());
        assertEquals("Something went wrong", error.getDebugMessage());
        assertNotNull(error.getTimestamp());
    }

    @Test
    void shouldCreateApiErrorWithStatusMessageAndException() {
        Exception ex = new IllegalArgumentException("Invalid input");
        ApiError error = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Validation failed", ex);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, error.getStatus());
        assertEquals("Validation failed", error.getMessage());
        assertEquals("Invalid input", error.getDebugMessage());
        assertNotNull(error.getTimestamp());
    }
}
