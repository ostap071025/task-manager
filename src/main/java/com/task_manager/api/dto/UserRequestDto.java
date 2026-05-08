package com.task_manager.api.dto;

public record UserRequestDto(
        String email,
        String password,
        String name
){

}
