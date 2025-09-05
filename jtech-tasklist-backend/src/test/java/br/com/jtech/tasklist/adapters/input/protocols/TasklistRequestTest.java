package br.com.jtech.tasklist.adapters.input.protocols;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TasklistRequestTest {

    @Test
    void shouldCreateTasklistRequestUsingBuilder() {
        TasklistRequest request = TasklistRequest.builder()
                .id("123")
                .title("Test Title")
                .description("Test Description")
                .status("PENDING")
                .build();

        assertEquals("123", request.getId());
        assertEquals("Test Title", request.getTitle());
        assertEquals("Test Description", request.getDescription());
        assertEquals("PENDING", request.getStatus());
        assertNull(request.getRequests());
    }

    @Test
    void shouldCreateNestedRequests() {
        TasklistRequest child = TasklistRequest.builder()
                .id("child-1")
                .title("Child Task")
                .description("Child Description")
                .status("COMPLETED")
                .build();

        TasklistRequest parent = TasklistRequest.builder()
                .id("parent-1")
                .title("Parent Task")
                .description("Parent Description")
                .status("PENDING")
                .requests(List.of(child))
                .build();

        assertEquals(1, parent.getRequests().size());
        assertEquals("Child Task", parent.getRequests().get(0).getTitle());
    }

    @Test
    void shouldUseNoArgsConstructorAndSetters() {
        TasklistRequest request = new TasklistRequest();
        request.setId("999");
        request.setTitle("Manual Task");
        request.setDescription("Manual Description");
        request.setStatus("PENDING");

        assertEquals("999", request.getId());
        assertEquals("Manual Task", request.getTitle());
        assertEquals("Manual Description", request.getDescription());
        assertEquals("PENDING", request.getStatus());
    }

    @Test
    void shouldSupportEqualsAndHashCode() {
        TasklistRequest r1 = TasklistRequest.builder()
                .id("abc")
                .title("Same")
                .description("Same")
                .status("PENDING")
                .build();

        TasklistRequest r2 = TasklistRequest.builder()
                .id("abc")
                .title("Same")
                .description("Same")
                .status("PENDING")
                .build();

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
