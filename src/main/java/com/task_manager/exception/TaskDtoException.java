package com.task_manager.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record TaskDtoException (
        String message,
        String details,
        LocalDateTime Timestamp
    ) {
}
