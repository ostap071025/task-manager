package com.task_manager.api;

import com.task_manager.api.dto.TaskDtoRequest;
import com.task_manager.api.dto.UserRequestDto;
import com.task_manager.api.dto.UserResponseDto;
import com.task_manager.domain.User.UserEntity;
import com.task_manager.domain.User.UserService;
import com.task_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;




    @PostMapping("/create")
    public UserResponseDto createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        log.info("Called createUser method");
        return userService.createUser(userRequestDto);
    }


    @GetMapping("/getOne/{id}")
    public UserResponseDto getOne(
            @PathVariable("id") Long id
    ) {
        log.info("Called getOne method with id: ", id);
        return userService.getOne(id);
    }


    @GetMapping("/getAll")
    public List<UserResponseDto> getAll() {
        log.info("Called getAll method");
        return userService.getAll();
    }


    @PatchMapping("/updateName/{id}")
    public UserResponseDto updateName(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        log.info("Called method updateName with id: ", id);
        return userService.updateName(id, userRequestDto);
    }

    @PatchMapping("/updatePassword/{id}")
    public UserResponseDto updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        log.info("Called method updatePassword with id: ", id);
        return userService.updatePassword(id, userRequestDto);
    }


    @DeleteMapping("/delete/{id}")
    public UserResponseDto deleteUser(
            @PathVariable("id") Long id
    ) {
        log.info("Task was deleted with id: ", id);
        return userService.deleteUser(id);
    }






}
