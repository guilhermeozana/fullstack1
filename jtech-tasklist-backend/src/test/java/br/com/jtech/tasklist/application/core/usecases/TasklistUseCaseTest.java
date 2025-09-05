package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.application.core.domains.TaskStatus;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.output.TasklistOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasklistUseCaseTest {

    private TasklistOutputGateway outputGateway;
    private TasklistUseCase useCase;

    @BeforeEach
    void setUp() {
        outputGateway = mock(TasklistOutputGateway.class);
        useCase = new TasklistUseCase(outputGateway);
    }

    @Test
    void shouldCreateTasklist() {
        Tasklist task = Tasklist.builder()
                .id("1")
                .title("New task")
                .description("Task description")
                .status(TaskStatus.PENDING)
                .build();

        when(outputGateway.create(task)).thenReturn(task);

        Tasklist result = useCase.create(task);

        assertNotNull(result);
        assertEquals("New task", result.getTitle());
        verify(outputGateway).create(task);
    }

    @Test
    void shouldFindTasklistById() {
        Tasklist task = Tasklist.builder()
                .id("1")
                .title("Find task")
                .description("Description")
                .status(TaskStatus.COMPLETED)
                .build();

        when(outputGateway.findById("1")).thenReturn(task);

        Tasklist result = useCase.findById("1");

        assertEquals("Find task", result.getTitle());
        assertEquals(TaskStatus.COMPLETED, result.getStatus());
        verify(outputGateway).findById("1");
    }

    @Test
    void shouldFindAllTasklists() throws Exception {
        List<Tasklist> tasks = List.of(
                Tasklist.builder().id("1").title("Task 1").description("Desc 1").status(TaskStatus.PENDING).build(),
                Tasklist.builder().id("2").title("Task 2").description("Desc 2").status(TaskStatus.COMPLETED).build()
        );

        when(outputGateway.findAll()).thenReturn(tasks);

        List<Tasklist> result = useCase.findAll();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        verify(outputGateway).findAll();
    }

    @Test
    void shouldUpdateTasklist() {
        Tasklist updated = Tasklist.builder()
                .id("1")
                .title("Updated")
                .description("New description")
                .status(TaskStatus.PENDING)
                .build();

        when(outputGateway.update("1", updated)).thenReturn(updated);

        Tasklist result = useCase.update("1", updated);

        assertEquals("Updated", result.getTitle());
        assertEquals(TaskStatus.PENDING, result.getStatus());
        verify(outputGateway).update("1", updated);
    }

    @Test
    void shouldDeleteTasklist() {
        doNothing().when(outputGateway).delete("1");

        useCase.delete("1");

        verify(outputGateway).delete("1");
    }
}
