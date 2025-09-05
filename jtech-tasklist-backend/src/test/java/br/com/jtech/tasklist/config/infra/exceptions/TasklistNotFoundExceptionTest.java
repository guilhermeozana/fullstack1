package br.com.jtech.tasklist.config.infra.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TasklistNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessage() {
        String id = "123";
        TasklistNotFoundException exception = new TasklistNotFoundException(id);

        assertEquals("Tasklist not found with id: 123", exception.getMessage());
    }

    @Test
    void shouldBeInstanceOfRuntimeException() {
        TasklistNotFoundException exception = new TasklistNotFoundException("456");

        assertTrue(exception instanceof RuntimeException);
    }
}
