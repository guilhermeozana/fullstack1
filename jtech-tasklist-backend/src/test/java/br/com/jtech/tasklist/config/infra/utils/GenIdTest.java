package br.com.jtech.tasklist.config.infra.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenIdTest {

    @Test
    void shouldGenerateNonNullRandomId() {
        String id = GenId.newId();
        assertNotNull(id, "Generated ID should not be null");
        assertFalse(id.isEmpty(), "Generated ID should not be empty");
    }

    @Test
    void shouldReturnProvidedIdIfNotNullOrEmpty() {
        String input = "custom-id-123";
        String result = GenId.newId(input);
        assertEquals(input, result, "Should return the provided ID if it's not null or empty");
    }

    @Test
    void shouldGenerateNewIdIfInputIsNull() {
        String result = GenId.newId(null);
        assertNotNull(result, "Generated ID should not be null when input is null");
        assertFalse(result.isEmpty(), "Generated ID should not be empty when input is null");
    }

    @Test
    void shouldGenerateNewIdIfInputIsEmpty() {
        String result = GenId.newId("");
        assertNotNull(result, "Generated ID should not be null when input is empty");
        assertFalse(result.isEmpty(), "Generated ID should not be empty when input is empty");
    }
}
