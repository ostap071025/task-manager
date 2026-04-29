package com.task_manager.api;

import com.task_manager.domain.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDtoResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDate deadline
) {
}
