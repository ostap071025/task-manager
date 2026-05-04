package com.task_manager.domain.Task;

import com.task_manager.api.dto.TaskDtoRequest;
import com.task_manager.api.dto.TaskDtoResponse;
import com.task_manager.exception.TaskNotFoundException;
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


    public TaskDtoResponse createTask(Long userId, TaskDtoRequest request) {
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


    public TaskDtoResponse getOne(Long id, TaskDtoRequest request) {
        var found = repository.findById(id)
                .orElseThrow(() ->  new TaskNotFoundException(id));
        return mapper.toDto(found);
    }

    public Page<TaskDtoResponse> getAll(Long userId, int page, int size, PrioritySort sortDirection) {


        Sort sort = sortDirection == PrioritySort.HIGH
                ? Sort.by("priorityLevel").descending()
                : Sort.by("priorityLevel").ascending();

        sort = sort.and(Sort.by("deadline").descending());

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TaskEntity> tasks = repository.findAllByUser_Id(userId, pageable);
        return tasks.map(mapper::toDto);

    }

    public TaskDtoResponse updateStatus(Long id, TaskDtoRequest request) {
        var found = repository.findById(id).
            orElseThrow(() -> new TaskNotFoundException(id));
        found.setStatus(
            request.status().equals(TaskStatus.DONE)
                ? TaskStatus.DONE
                : TaskStatus.FAILED
        );
        repository.save(found);
        return mapper.toDto(found);
    }

    public TaskDtoResponse deleteTask(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        repository.delete(entity);
        return mapper.toDto(entity);

    }
}