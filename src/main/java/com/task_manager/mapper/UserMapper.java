package com.task_manager.mapper;

import com.task_manager.api.dto.UserRequestDto;
import com.task_manager.api.dto.UserResponseDto;
import com.task_manager.api.dto.UserTaskDto;
import com.task_manager.domain.User.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Locale;
@Component
public class UserMapper {
    public UserEntity userToEntity(UserRequestDto userRequest) {
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
}

