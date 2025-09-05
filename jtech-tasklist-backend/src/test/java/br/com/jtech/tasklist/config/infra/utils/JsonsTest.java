package br.com.jtech.tasklist.config.infra.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonsTest {

    static class Sample {
        public String name;
        public int age;
        public LocalDateTime createdAt;

        public Sample() {}

        public Sample(String name, int age, LocalDateTime createdAt) {
            this.name = name;
            this.age = age;
            this.createdAt = createdAt;
        }
    }

    @Test
    void shouldSerializeObjectToJsonString() {
        Sample sample = new Sample("Guilherme", 30, LocalDateTime.of(2025, 9, 4, 6, 58));
        String json = Jsons.toJsonString(sample);

        assertNotNull(json);
        assertTrue(json.contains("Guilherme"));
        assertTrue(json.contains("2025-09-04 06:58:00")); // Verifica o formato customizado
    }

    @Test
    void shouldDeserializeJsonToObject() {
        String json = "{\"name\":\"Guilherme\",\"age\":30,\"createdAt\":\"2025-09-04 06:58:00\"}";
        Sample sample = Jsons.parseJsonString(json, Sample.class);

        assertNotNull(sample);
        assertEquals("Guilherme", sample.name);
        assertEquals(30, sample.age);
        assertEquals(LocalDateTime.of(2025, 9, 4, 6, 58), sample.createdAt);
    }

    @Test
    void shouldDeserializeJsonToMapUsingTypeReference() {
        String json = "{\"key\":\"value\",\"number\":42}";
        Map<String, Object> result = Jsons.parseJsonString(json, new TypeReference<Map<String, Object>>() {});

        assertNotNull(result);
        assertEquals("value", result.get("key"));
        assertEquals(42, result.get("number"));
    }

    @Test
    void shouldReturnNullWhenSerializingNullObject() {
        String json = Jsons.toJsonString(null);
        assertNull(json);
    }

    @Test
    void shouldReturnNullWhenParsingBlankJson() {
        Sample result = Jsons.parseJsonString("   ", Sample.class);
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenParsingBlankJsonWithTypeReference() {
        Map<String, Object> result = Jsons.parseJsonString("   ", new TypeReference<Map<String, Object>>() {});
        assertNull(result);
    }
}
