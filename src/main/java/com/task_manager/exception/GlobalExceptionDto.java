package com.task_manager.exception;

import com.task_manager.exception.task.TaskDtoException;
import com.task_manager.exception.task.TaskNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TaskDtoException handleTaskNotFoundException(TaskNotFoundException e) {
        return new TaskDtoException(
                "Task not found",
                e.getMessage(),
                LocalDateTime.now()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
        MethodArgumentNotValidException ex
    ) {
        System.out.println("VALIDATION HANDLER WORKS");
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

}











