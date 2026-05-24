package com.task_manager.mapper;

import com.task_manager.api.dto.auth.AuthResponseDto;
import com.task_manager.api.dto.auth.RegisterRequestDto;
import com.task_manager.api.dto.users.*;
import com.task_manager.domain.User.UserEntity;
import com.task_manager.domain.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserMapper(PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserEntity userToEntity(UserCreateRequestDto userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.email());
        userEntity.setPassword(userRequest.password());
        userEntity.setName(userRequest.name());
        return userEntity;
    }

    public UserResponseDto userToDto(UserEntity userEntity) {
        UserResponseDto userResponse = new UserResponseDto(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getJoinedAt(),
                userEntity.getTasks()
                        .stream()
                        .map(task -> new UserTaskDto(task.getTitle(), task.getDescription()))
                        .toList()
        );
        return userResponse;
    }



    public void updateUser(UserEntity entity, UserUpdateRequestDto requestDto) {
        if (requestDto.name() != null && !requestDto.name().equals("string")) {
            entity.setName(requestDto.name());
        }

        if (requestDto.password() != null && !requestDto.password().equals("string")) {
            entity.setPassword(requestDto.password());
        }

        if (requestDto.email() != null && !requestDto.email().equals("string")) {
            entity.setEmail(requestDto.email());
        }
    }




    public UserEntity toEntity(RegisterRequestDto request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.email());
        user.setName(request.name());
        return user;
    }


    public AuthResponseDto toDto(UserEntity entity, String token) {
        AuthResponseDto responseDto = new AuthResponseDto(
                token,
                entity.getId(),
                entity.getEmail(),
                entity.getRole()
        );

        return responseDto;
    }
}

