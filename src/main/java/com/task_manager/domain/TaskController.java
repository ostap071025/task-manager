package com.task_manager.domain;

import com.task_manager.api.TaskDtoRequest;
import com.task_manager.api.TaskDtoResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class TaskController {

    private final TaskService service;

    @PostMapping("create")
    public TaskDtoResponse createTask(
            @Valid @RequestBody TaskDtoRequest request
    ) {
        log.info("Called method createTask with id={}");
        return service.createTask(request);
    }

    @GetMapping("/getById/{id}")
    public TaskDtoResponse getOne(
            @PathVariable("id") Long id,
            @Valid TaskDtoRequest request
    ){
        log.info("Called method getOne with id={}", id);
        return service.getOne(id, request);
    }

    @GetMapping("/getAll")
    public List<TaskDtoResponse> getAll(){
        log.info("Called method getAll");
        return service.getAll();
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


