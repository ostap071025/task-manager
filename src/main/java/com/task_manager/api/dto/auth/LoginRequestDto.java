package com.task_manager.api.dto.auth;

public record LoginRequestDto (
        String email,
        String password
) {
}
