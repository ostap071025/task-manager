package com.task_manager.exception.user;

import java.time.LocalDateTime;

public record UserDtoException (
        String message,
        String details,
        LocalDateTime timestamp
){

}
