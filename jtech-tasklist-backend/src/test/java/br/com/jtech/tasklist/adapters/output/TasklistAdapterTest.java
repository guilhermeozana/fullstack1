package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.application.core.domains.TaskStatus;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.config.infra.exceptions.TasklistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasklistAdapterTest {

    private TasklistRepository repository;
    private TasklistAdapter adapter;

    @BeforeEach
    void setup() {
        repository = mock(TasklistRepository.class);
        adapter = new TasklistAdapter(repository);
    }

    @Test
    void shouldCreateTasklist() {
        var tasklist = new Tasklist(UUID.randomUUID().toString(), "title", "desc", TaskStatus.IN_PROGRESS);
        var entity = tasklist.toEntity();
        entity.setId(UUID.randomUUID());

        when(repository.save(any())).thenReturn(entity);

        var result = adapter.create(tasklist);

        assertNotNull(result);
        assertEquals(tasklist.getTitle(), result.getTitle());
        verify(repository).save(any());
    }

    @Test
    void shouldFindTasklistById() {
        var id = UUID.randomUUID();
        var entity = new TasklistEntity();
        entity.setId(id);
        entity.setTitle("Sample");
        entity.setDescription("Desc");
        entity.setStatus("IN_PROGRESS");

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        var result = adapter.findById(id.toString());

        assertNotNull(result);
        assertEquals("Sample", result.getTitle());
        verify(repository).findById(id);
    }

    @Test
    void shouldThrowWhenTasklistNotFoundById() {
        var id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TasklistNotFoundException.class, () -> adapter.findById(id.toString()));
    }

    @Test
    void shouldFindAllTasklists() {
        var entity = new TasklistEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle("All");
        entity.setDescription("Desc");
        entity.setStatus("IN_PROGRESS");

        when(repository.findAll()).thenReturn(List.of(entity));

        var result = adapter.findAll();

        assertEquals(1, result.size());
        assertEquals("All", result.get(0).getTitle());
    }

    @Test
    void shouldUpdateTasklist() {
        var id = UUID.randomUUID();
        var tasklist = new Tasklist(id.toString(), "Updated", "New Desc", TaskStatus.COMPLETED);
        var entity = tasklist.toEntity();
        entity.setId(UUID.fromString(id.toString()));

        when(repository.findById(id)).thenReturn(Optional.of(new TasklistEntity()));
        when(repository.save(any())).thenReturn(entity);

        var result = adapter.update(id.toString(), tasklist);

        assertEquals("Updated", result.getTitle());
        assertEquals("COMPLETED", result.getStatus().toString());
    }

    @Test
    void shouldThrowWhenUpdatingNonexistentTasklist() {
        var id = UUID.randomUUID();
        var tasklist = new Tasklist(id.toString(), "Updated", "New Desc", TaskStatus.COMPLETED);

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TasklistNotFoundException.class, () -> adapter.update(id.toString(), tasklist));
    }

    @Test
    void shouldDeleteTasklist() {
        var id = UUID.randomUUID();
        var entity = new TasklistEntity();
        entity.setId(UUID.fromString(id.toString()));

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        adapter.delete(id.toString());

        verify(repository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeletingNonexistentTasklist() {
        var id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TasklistNotFoundException.class, () -> adapter.delete(id.toString()));
    }
}
