package com.task_manager.exception;

import java.time.LocalDateTime;

public record UserDtoException (
        String message,
        String details,
        LocalDateTime timestamp
){
}
