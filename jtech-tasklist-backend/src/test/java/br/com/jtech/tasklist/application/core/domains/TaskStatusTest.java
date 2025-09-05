package br.com.jtech.tasklist.application.core.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    void shouldConvertValidLowercaseStringToEnum() {
        TaskStatus status = TaskStatus.fromString("pending");
        assertEquals(TaskStatus.PENDING, status);
    }

    @Test
    void shouldConvertValidUppercaseStringToEnum() {
        TaskStatus status = TaskStatus.fromString("COMPLETED");
        assertEquals(TaskStatus.COMPLETED, status);
    }

    @Test
    void shouldConvertMixedCaseStringToEnum() {
        TaskStatus status = TaskStatus.fromString("PeNdInG");
        assertEquals(TaskStatus.PENDING, status);
    }

    @Test
    void shouldReturnNullForNullInput() {
        TaskStatus status = TaskStatus.fromString(null);
        assertNull(status);
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            TaskStatus.fromString("invalid_status");
        });
    }
}
