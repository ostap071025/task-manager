package com.task_manager.api.dto;

import com.task_manager.domain.User.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDto (
        Long id,
        String email,
        String password,
        String name,
        LocalDate joinedAt,
        List<UserTaskDto> tasks
){
}
