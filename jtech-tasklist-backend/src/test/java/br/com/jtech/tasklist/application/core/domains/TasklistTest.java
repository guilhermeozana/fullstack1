package br.com.jtech.tasklist.application.core.domains;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TasklistTest {

    @Test
    void shouldConvertFromEntityToDomain() {
        TasklistEntity entity = TasklistEntity.builder()
                .id(UUID.randomUUID())
                .title("Test Title")
                .description("Test Description")
                .status("PENDING")
                .build();

        Tasklist tasklist = Tasklist.of(entity);

        assertEquals(entity.getId().toString(), tasklist.getId());
        assertEquals("Test Title", tasklist.getTitle());
        assertEquals("Test Description", tasklist.getDescription());
        assertEquals(TaskStatus.PENDING, tasklist.getStatus());
    }

    @Test
    void shouldConvertFromRequestToDomain() {
        TasklistRequest request = new TasklistRequest();
        request.setId("123");
        request.setTitle("Request Title");
        request.setDescription("Request Description");
        request.setStatus("COMPLETED");

        Tasklist tasklist = Tasklist.of(request);

        assertEquals("123", tasklist.getId());
        assertEquals("Request Title", tasklist.getTitle());
        assertEquals("Request Description", tasklist.getDescription());
        assertEquals(TaskStatus.COMPLETED, tasklist.getStatus());
    }

    @Test
    void shouldConvertDomainToEntity() {
        Tasklist tasklist = Tasklist.builder()
                .id(UUID.randomUUID().toString())
                .title("Domain Title")
                .description("Domain Description")
                .status(TaskStatus.PENDING)
                .build();

        TasklistEntity entity = tasklist.toEntity();

        assertEquals(UUID.fromString(tasklist.getId()), entity.getId());
        assertEquals("Domain Title", entity.getTitle());
        assertEquals("Domain Description", entity.getDescription());
        assertEquals("PENDING", entity.getStatus());
    }

    @Test
    void shouldConvertListOfEntitiesToDomainList() {
        TasklistEntity entity1 = TasklistEntity.builder()
                .id(UUID.randomUUID())
                .title("Task 1")
                .description("Desc 1")
                .status("PENDING")
                .build();

        TasklistEntity entity2 = TasklistEntity.builder()
                .id(UUID.randomUUID())
                .title("Task 2")
                .description("Desc 2")
                .status("COMPLETED")
                .build();

        List<Tasklist> result = Tasklist.of(List.of(entity1, entity2));

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals(TaskStatus.PENDING, result.get(0).getStatus());
        assertEquals("Task 2", result.get(1).getTitle());
        assertEquals(TaskStatus.COMPLETED, result.get(1).getStatus());
    }
}
