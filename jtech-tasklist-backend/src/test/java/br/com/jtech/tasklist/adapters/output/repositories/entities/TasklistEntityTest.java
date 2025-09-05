package br.com.jtech.tasklist.adapters.output.repositories.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TasklistEntityTest {

    @Test
    void shouldCreateEntityUsingBuilder() {
        UUID id = UUID.randomUUID();
        TasklistEntity entity = TasklistEntity.builder()
                .id(id)
                .title("Test Title")
                .description("Test Description")
                .status("PENDING")
                .build();

        assertEquals(id, entity.getId());
        assertEquals("Test Title", entity.getTitle());
        assertEquals("Test Description", entity.getDescription());
        assertEquals("PENDING", entity.getStatus());
    }

    @Test
    void shouldUseNoArgsConstructorAndSetters() {
        UUID id = UUID.randomUUID();
        TasklistEntity entity = new TasklistEntity();
        entity.setId(id);
        entity.setTitle("Manual Title");
        entity.setDescription("Manual Description");
        entity.setStatus("COMPLETED");

        assertEquals(id, entity.getId());
        assertEquals("Manual Title", entity.getTitle());
        assertEquals("Manual Description", entity.getDescription());
        assertEquals("COMPLETED", entity.getStatus());
    }

    @Test
    void shouldSupportEqualityAndHashCode() {
        UUID id = UUID.randomUUID();

        TasklistEntity e1 = TasklistEntity.builder()
                .id(id)
                .title("Equal")
                .description("Same")
                .status("PENDING")
                .build();

        TasklistEntity e2 = TasklistEntity.builder()
                .id(id)
                .title("Equal")
                .description("Same")
                .status("PENDING")
                .build();

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }
}
