package com.task_manager.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
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

//    @ExceptionHandler(Exception.class)
//    public TaskDtoException handleAllExceptions(Exception e) {
//        return new TaskDtoException(
//                "Internal server error",
//                e.getMessage(),
//                LocalDateTime.now()
//        );
//    }

}
