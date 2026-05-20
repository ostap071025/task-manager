package com.task_manager.api.dto.tasks;

import com.task_manager.domain.Task.TaskPriority;
import com.task_manager.domain.Task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TaskDtoCreateRequest (
        @Schema(example = "your title")
        @NotBlank(message = "Title cannot be blank")
        @Pattern(
                regexp = ".*\\p{L}.*",
                message = "Field must contain letters"
        )
        String title,

        @Schema(example = "your description")
        @NotBlank(message = "Description cannot be blank")
        @Size(max = 100, message = "Description must be less than 100 characters")
        String description,

        @NotNull(message = "Status cannot be null")
        TaskStatus status,

        @FutureOrPresent(message = "Deadline cannot be in the past")
        LocalDate deadline,

        @NotNull(message = "Priority cannot be null")
        TaskPriority priority,

        @NotNull(message = "User id cannot be null")
        Long userId
) {
}
