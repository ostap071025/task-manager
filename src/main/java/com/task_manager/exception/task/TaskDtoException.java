package com.task_manager.exception.task;

import java.time.LocalDateTime;

public record TaskDtoException (
        String message,
        String details,
        LocalDateTime Timestamp
    ) {
}
