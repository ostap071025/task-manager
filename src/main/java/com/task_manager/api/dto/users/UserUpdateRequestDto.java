package com.task_manager.api.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDto(
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must contain at least 6 characters")
        String password,

        @NotBlank(message = "Name cannot be blank")
        String name
    ) {
}
