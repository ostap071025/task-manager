package com.task_manager.domain.User;

import com.task_manager.api.dto.UserRequestDto;
import com.task_manager.api.dto.UserResponseDto;
import com.task_manager.exception.TaskNotFoundException;
import com.task_manager.exception.UserNotFoundException;
import com.task_manager.mapper.UserMapper;
import com.task_manager.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = userMapper.userToEntity(userRequestDto);
        var saved = userRepository.save(userEntity);
        return userMapper.userToDto(saved);
    }

    public UserResponseDto getOne(Long id) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.userToDto(found);
    }


    public List<UserResponseDto> getAll() {
        List<UserEntity> userList = userRepository.findAll();
        var userDtoList = userList.stream().map(userMapper::userToDto).toList();
        return userDtoList;
    }


    public UserResponseDto updateName(Long id, UserRequestDto userRequestDto) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        found.setName(userRequestDto.name());
        var saved = userRepository.save(found);
        return userMapper.userToDto(saved);
    }

    public UserResponseDto updatePassword(Long id, UserRequestDto userRequestDto) {
        var found = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        found.setPassword(userRequestDto.password());
        var saved = userRepository.save(found);
        return userMapper.userToDto(saved);
    }


    public UserResponseDto deleteUser(Long id) {
        var founded = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return userMapper.userToDto(founded);
    }
}
