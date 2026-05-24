package com.task_manager.api.dto.auth;

import com.task_manager.domain.authentication.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthResponseDto (
        String token,
        Long id,
        String email,
        UserRole role
) {
}
