package com.task_manager.domain.service;

import com.task_manager.api.dto.auth.AuthResponseDto;
import com.task_manager.api.dto.auth.LoginRequestDto;
import com.task_manager.api.dto.auth.RegisterRequestDto;
import com.task_manager.domain.User.UserEntity;
import com.task_manager.domain.authentication.UserRole;
import com.task_manager.mapper.UserMapper;
import com.task_manager.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository repository;
    private final UserMapper mapper;


    public AuthResponseDto registerUser(RegisterRequestDto request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        var entity = mapper.toEntity(request);
        var user = repository.save(entity);
        user.setRole(UserRole.USER);
        return mapper.toDto(user, "Registration successful");
    }

    public AuthResponseDto loginUser(@Valid LoginRequestDto request) {
        UserEntity entity = repository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!entity.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        if (entity.getRole() == null) {
            entity.setRole(UserRole.USER);
        }
        return mapper.toDto(entity, "welcome back");
    }
}
