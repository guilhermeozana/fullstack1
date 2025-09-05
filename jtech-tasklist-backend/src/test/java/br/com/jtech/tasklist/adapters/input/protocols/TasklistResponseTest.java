package br.com.jtech.tasklist.adapters.input.protocols;

import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.application.core.domains.TaskStatus;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class  TasklistResponseTest {

    @Test
    void shouldConvertFromTasklistDomain() {
        Tasklist task = Tasklist.builder()
                .id("123")
                .title("Test Title")
                .description("Test Description")
                .status(TaskStatus.PENDING)
                .build();

        TasklistResponse response = TasklistResponse.of(task);

        assertEquals("123", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
        assertEquals("PENDING", response.getStatus());
        assertNull(response.getResponses());
    }

    @Test
    void shouldConvertFromTasklistEntity() {
        TasklistEntity entity = TasklistEntity.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                .title("Entity Title")
                .description("Entity Description")
                .status("COMPLETED")
                .build();

        TasklistResponse response = TasklistResponse.of(entity);

        assertEquals("00000000-0000-0000-0000-000000000001", response.getId());
        assertEquals("Entity Title", response.getTitle());
        assertEquals("Entity Description", response.getDescription());
        assertEquals("COMPLETED", response.getStatus());
    }

    @Test
    void shouldConvertListOfEntitiesToResponseList() {
        List<TasklistEntity> entities = List.of(
                TasklistEntity.builder()
                        .id(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                        .title("First")
                        .description("First Desc")
                        .status("PENDING")
                        .build(),
                TasklistEntity.builder()
                        .id(UUID.fromString("00000000-0000-0000-0000-000000000003"))
                        .title("Second")
                        .description("Second Desc")
                        .status("COMPLETED")
                        .build()
        );

        TasklistResponse response = TasklistResponse.of(entities);

        assertNotNull(response.getResponses());
        assertEquals(2, response.getResponses().size());
        assertEquals("First", response.getResponses().get(0).getTitle());
        assertEquals("Second", response.getResponses().get(1).getTitle());
    }
}
