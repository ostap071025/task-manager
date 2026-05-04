package com.task_manager.api;

import com.task_manager.api.dto.TaskDtoRequest;
import com.task_manager.api.dto.TaskDtoResponse;
import com.task_manager.domain.Task.PrioritySort;
import com.task_manager.domain.Task.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class TaskController {

    private final TaskService service;

    @PostMapping("create/users/{userId}/tasks")
    public TaskDtoResponse createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskDtoRequest request
    ) {
        log.info("Called method createTask with id={}");
        return service.createTask(userId, request);
    }

    @GetMapping("/getById/{id}")
    public TaskDtoResponse getOne(
            @PathVariable("id") Long id,
            @Valid TaskDtoRequest request
    ){
        log.info("Called method getOne with id={}", id);
        return service.getOne(id, request);
    }

    @GetMapping("/users/{userId}/tasks")
    public Page<TaskDtoResponse> getAll(
            @PathVariable("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "HIGH") PrioritySort sortPriority
            ){
        log.info("Called method getAll");
        return service.getAll(userId, page, size, sortPriority);
    }

    @PutMapping("/updateStatus/{id}")
    public TaskDtoResponse updateStatus(
            @PathVariable("id") Long id,
            @Valid TaskDtoRequest request
    ) {
        log.info("Called updateStatus for id={}", id);
        return service.updateStatus(id, request);
    }

    @DeleteMapping("/deleteStatus/{id}")
    public TaskDtoResponse deleteTask(
            @Valid @PathVariable("id") Long id
    ) {
        log.info("Called deleteTask with id={}", id);
        return service.deleteTask(id);
    }




}


