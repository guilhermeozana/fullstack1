package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.TaskStatus;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.TasklistInputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TasklistControllerTest {

    private TasklistInputGateway gateway;
    private TasklistController controller;

    @BeforeEach
    void setup() {
        gateway = mock(TasklistInputGateway.class);
        controller = new TasklistController(gateway);
    }

    @Test
    void shouldCreateTasklist() {
        TasklistRequest request = TasklistRequest.builder()
                .id("1")
                .title("Test Task")
                .description("Test Description")
                .status("PENDING")
                .build();

        Tasklist created = Tasklist.builder()
                .id("1")
                .title("Test Task")
                .description("Test Description")
                .status(TaskStatus.PENDING)
                .build();

        when(gateway.create(any(Tasklist.class))).thenReturn(created);

        ResponseEntity<TasklistResponse> response = controller.create(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(created.getTitle(), response.getBody().getTitle());
        verify(gateway).create(any(Tasklist.class));
    }

    @Test
    void shouldReturnTasklistById() {
        String id = "123";
        Tasklist task = Tasklist.builder()
                .id(id)
                .title("Sample Task")
                .description("Sample Description")
                .status(TaskStatus.PENDING)
                .build();

        when(gateway.findById(id)).thenReturn(task);

        ResponseEntity<TasklistResponse> response = controller.getById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Sample Task", response.getBody().getTitle());
        verify(gateway).findById(id);
    }

    @Test
    void shouldListAllTasklists() {
        List<Tasklist> tasks = List.of(
                Tasklist.builder().id("1").title("Task 1").description("Desc 1").status(TaskStatus.PENDING).build(),
                Tasklist.builder().id("2").title("Task 2").description("Desc 2").status(TaskStatus.COMPLETED).build()
        );

        when(gateway.findAll()).thenReturn(tasks);

        ResponseEntity<List<TasklistResponse>> response = controller.listAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(gateway).findAll();
    }

    @Test
    void shouldUpdateTasklist() {
        String id = "456";
        TasklistRequest request = TasklistRequest.builder()
                .id(id)
                .title("Updated Task")
                .description("Updated Description")
                .status("COMPLETED")
                .build();

        Tasklist updated = Tasklist.builder()
                .id(id)
                .title("Updated Task")
                .description("Updated Description")
                .status(TaskStatus.COMPLETED)
                .build();

        when(gateway.update(eq(id), any(Tasklist.class))).thenReturn(updated);

        ResponseEntity<TasklistResponse> response = controller.update(id, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Task", response.getBody().getTitle());
        verify(gateway).update(eq(id), any(Tasklist.class));
    }

    @Test
    void shouldDeleteTasklist() {
        String id = "789";
        doNothing().when(gateway).delete(id);

        ResponseEntity<Void> response = controller.delete(id);

        assertEquals(204, response.getStatusCodeValue());
        verify(gateway).delete(id);
    }
}
