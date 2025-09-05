package br.com.jtech.tasklist.adapters.input.protocols;

import br.com.jtech.tasklist.application.core.domains.TaskStatus;
import br.com.jtech.tasklist.config.infra.annotations.EnumValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasklistRequest implements Serializable {

    private String id;

    @NotBlank(message = "Title is required and cannot be blank")
    private String title;

    private String description;

    @NotBlank(message = "Status is required")
    @EnumValidator(enumClass = TaskStatus.class, message = "Status must be one of: PENDING, IN_PROGRESS, COMPLETED")
    private String status;

    private List<TasklistRequest> requests;
}
