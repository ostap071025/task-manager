package com.task_manager.api.dto.auth;

import com.task_manager.domain.authentication.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record RegisterRequestDto (

        @NotBlank(message = "email cannot be blank")
        @Email(message = "Your email isn't valid")
        String email,

        @Pattern(
                regexp = ".*\\p{L}.*",
                message = "Field must contain letters"
        )
        @Schema(example = "your_password")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password cannot be less than 6 characters")
        String password,

        @NotBlank(message = "Name cannot be blank")
        String name
) {

}
