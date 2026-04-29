package com.task_manager.domain.exception;

import com.task_manager.api.TaskDtoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionDto {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TaskDtoException handleEntityNotFoundException (EntityNotFoundException e){
        return new TaskDtoException(
                "Task not found",
                e.getMessage(),
                LocalDateTime.now()
        );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TaskDtoException handleIllegalArgumentException(IllegalArgumentException e) {
        return new TaskDtoException(
                "Bad request",
                e.getMessage(),
                LocalDateTime.now()
        );

    }

    @ExceptionHandler(Exception.class)
    public TaskDtoException handleAllExceptions(Exception e) {
        return new TaskDtoException(
                "Internal server error",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

}
