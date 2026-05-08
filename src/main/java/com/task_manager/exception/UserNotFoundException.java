package com.task_manager.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (Long id) {
        super("not found with id " + id);
    }
}
