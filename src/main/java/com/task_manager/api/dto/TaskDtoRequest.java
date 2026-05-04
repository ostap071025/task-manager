package com.task_manager.api.dto;

import com.task_manager.domain.Task.TaskPriority;
import com.task_manager.domain.Task.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record TaskDtoRequest(
        @NotBlank String title,
        @NotBlank String description,
        TaskStatus status,
        @FutureOrPresent LocalDate deadline,
        TaskPriority priority,
        int priorityLevel,
        Long userId
) {
}
