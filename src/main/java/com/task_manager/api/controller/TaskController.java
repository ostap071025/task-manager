package com.task_manager.api.controller;

import com.task_manager.api.dto.tasks.TaskDtoCreateRequest;
import com.task_manager.api.dto.tasks.TaskDtoResponse;
import com.task_manager.api.dto.tasks.TaskDtoUpdateRequest;
import com.task_manager.domain.Task.PrioritySort;
import com.task_manager.domain.Task.TaskService;
import com.task_manager.domain.Task.TaskStatus;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController("tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping("/{userId}")
    public TaskDtoResponse createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskDtoCreateRequest request
    ) {
        log.info("Called method createTask with id={}");
        return service.createTask(userId, request);
    }

    @GetMapping("/{id}")
    public TaskDtoResponse getOne(
            @PathVariable("id") Long id
    ){
        log.info("Called method getOne with id={}", id);
        return service.getOne(id);
    }

    @GetMapping("/tasks")
    public Page<TaskDtoResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "HIGH") PrioritySort sortPriority,
            @RequestParam(defaultValue = "IN_PROCESS")TaskStatus taskStatus
            ){
        log.info("Called method getAll");
        return service.getAll(page, size, sortPriority, taskStatus);
    }

    @PatchMapping("/{id}")
    public TaskDtoResponse updateTask(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskDtoUpdateRequest request
    ) {
        log.info("Called updateStatus for id={}", id);
        return service.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public TaskDtoResponse deleteTask(
            @PathVariable("id") Long id
    ) {
        log.info("Called deleteTask with id={}", id);
        return service.deleteTask(id);
    }


}


