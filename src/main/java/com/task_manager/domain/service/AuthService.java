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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthResponseDto registerUser(RegisterRequestDto request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        var entity = mapper.toEntity(request);
        var hashedPassword = passwordEncoder.encode(request.password());
        entity.setPassword(hashedPassword);
        entity.setRole(UserRole.USER);
        var user = repository.save(entity);
        String token = jwtService.generateToken(user);

        return mapper.toDto(user, token);
    }

    public AuthResponseDto loginUser(@Valid LoginRequestDto request) {
        UserEntity entity = repository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(), entity.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (entity.getRole() == null) {
            entity.setRole(UserRole.USER);
        }
        String token = jwtService.generateToken(entity);

        return mapper.toDto(entity, token);
    }
}
