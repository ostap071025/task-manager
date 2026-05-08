package com.task_manager.api.dto;

import com.task_manager.domain.Task.TaskPriority;
import com.task_manager.domain.Task.TaskStatus;
import com.task_manager.domain.User.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskDtoResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDate deadline,
        TaskPriority priority,
        Long userId,
        String name
) {
}
