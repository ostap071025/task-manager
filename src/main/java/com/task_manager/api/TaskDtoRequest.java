package com.task_manager.api;

import com.task_manager.domain.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record TaskDtoRequest(
        @NotBlank String title,
        @NotBlank String description,
        TaskStatus status,
        @FutureOrPresent LocalDate deadline
) {
}
