package com.task_manager.api.controller;

import com.task_manager.api.dto.tasks.TaskDtoResponse;
import com.task_manager.api.dto.users.UserCreateRequestDto;
import com.task_manager.api.dto.users.UserResponseDto;
import com.task_manager.api.dto.users.UserUpdateRequestDto;
import com.task_manager.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;




    @PostMapping("")
    public UserResponseDto createUser(
            @Valid @RequestBody UserCreateRequestDto request
    ) {
        log.info("Called createUser method");
        return service.createUser(request);
    }


    @GetMapping("/{id}")
    public UserResponseDto getOne(
            @PathVariable("id") Long id
    ) {
        log.info("Called getOne method with id: ", id);
        return service.getOne(id);
    }


    @GetMapping("")
    public Page<UserResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name
    ) {
        log.info("Called getAll method");
        return service.getAll(page, size, email, name);
    }


    @PatchMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        log.info("Called method updateName with id: ", id);
        return service.updateUser(id, userUpdateRequestDto);
    }


    @DeleteMapping("/{id}")
    public UserResponseDto deleteUser(
            @PathVariable("id") Long id
    ) {
        log.info("Task was deleted with id: ", id);
        return service.deleteUser(id);
    }


    @GetMapping("/{user_id}")
    public List<TaskDtoResponse> getUserTask(
            @PathVariable("user_id") Long user_id
    ) {
        return service.getUserTask(user_id);
    }






}
