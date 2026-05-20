package com.task_manager.api.dto.users;

import java.time.LocalDate;
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
