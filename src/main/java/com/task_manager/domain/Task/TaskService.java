package com.task_manager.domain.Task;

import com.task_manager.api.dto.tasks.TaskDtoCreateRequest;
import com.task_manager.api.dto.tasks.TaskDtoResponse;
import com.task_manager.api.dto.tasks.TaskDtoUpdateRequest;
import com.task_manager.exception.task.TaskNotFoundException;
import com.task_manager.mapper.TaskMapper;
import com.task_manager.repository.TaskRepository;
import com.task_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;


    public TaskDtoResponse createTask(Long userId, TaskDtoCreateRequest request) {
        var entity = mapper.toEntity(request);
        if (entity.getStatus() != TaskStatus.IN_PROCESS
            && entity.getStatus() != TaskStatus.DONE
            && entity.getStatus() != TaskStatus.FAILED) {
            throw new IllegalArgumentException("Illegal argument in field \"status\"");
        }
        var user = userRepository.findById(userId).
                orElseThrow();

        entity.setUser(user);

        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    public TaskDtoResponse getOne(Long id) {
        var found = repository.findById(id)
                .orElseThrow(() ->  new TaskNotFoundException(id));
        return mapper.toDto(found);
    }

    public Page<TaskDtoResponse> getAll(int page, int size, PrioritySort sortDirection, TaskStatus taskStatus) {


        Sort sort = sortDirection == PrioritySort.HIGH
                ? Sort.by("priorityLevel").descending()
                : Sort.by("priorityLevel").ascending();

        sort = sort.and(Sort.by("deadline").descending());
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskEntity> tasks;

        if (taskStatus != null) {
            tasks = repository.findByStatus(taskStatus, pageable);
        } else {
            tasks = repository.findAll(pageable);
        }

        return tasks.map(mapper::toDto);

    }

    public TaskDtoResponse updateTask(Long id, TaskDtoUpdateRequest request) {
        var found = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        mapper.updateEntity(found, request);
        var saved = repository.save(found);
        return mapper.toDto(saved);
    }

    public TaskDtoResponse deleteTask(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        repository.delete(entity);
        return mapper.toDto(entity);

    }
}