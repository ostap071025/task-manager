package com.task_manager.exception.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (Long id) {
        super("not found with id " + id);
    }
}
