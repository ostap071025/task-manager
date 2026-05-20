package com.task_manager.api.dto.tasks;

import com.task_manager.domain.Task.TaskPriority;
import com.task_manager.domain.Task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TaskDtoUpdateRequest(
        String title,

        String description,

        TaskStatus status,

        LocalDate deadline,

        TaskPriority priority
) {
}
