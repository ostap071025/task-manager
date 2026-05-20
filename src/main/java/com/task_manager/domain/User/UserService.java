package com.task_manager.domain.User;

import com.task_manager.api.dto.tasks.TaskDtoResponse;
import com.task_manager.api.dto.users.UserCreateRequestDto;
import com.task_manager.api.dto.users.UserResponseDto;
import com.task_manager.api.dto.users.UserUpdateRequestDto;
import com.task_manager.exception.user.UserNotFoundException;
import com.task_manager.mapper.TaskMapper;
import com.task_manager.mapper.UserMapper;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public UserResponseDto createUser(UserCreateRequestDto request) {
        UserEntity userEntity = userMapper.userToEntity(request);
//        if (userRepository.existsByEmail(request.email())) {
//            throw new IllegalArgumentException("User with this email already exists");
//        }
        var saved = userRepository.save(userEntity);
        return userMapper.userToDto(saved);
    }

    public UserResponseDto getOne(Long id) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.userToDto(found);
    }


    public Page<UserResponseDto> getAll(
            int page,
            int size,
            String email,
            String name
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> users;
        if (email != null) {
            users = userRepository.findByEmail(email, pageable);
        } else if (name != null) {
            users = userRepository.findByName(name, pageable);
        } else {
            users = userRepository.findAll(pageable);
        }
        return users.map(userMapper::userToDto);
    }


    public UserResponseDto updateUser(Long id, UserUpdateRequestDto request) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userMapper.updateUser(found, request);
        var saved = userRepository.save(found);
        return userMapper.userToDto(saved);
    }


    public UserResponseDto deleteUser(Long id) {
        var found = userRepository.findByIdWithTasks(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        var response = userMapper.userToDto(found);
        userRepository.delete(found);
        return response;
    }

    public List<TaskDtoResponse> getUserTask(Long user_id) {
        return taskRepository.findAllByUser_Id(user_id)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
