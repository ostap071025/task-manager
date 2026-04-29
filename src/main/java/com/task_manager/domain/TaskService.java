package com.task_manager.domain;

import com.task_manager.api.TaskDtoRequest;
import com.task_manager.api.TaskDtoResponse;
import com.task_manager.domain.exception.GlobalExceptionDto;
import com.task_manager.domain.exception.TaskDtoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskDtoResponse createTask(TaskDtoRequest request) {

        var entity = mapper.toEntity(request);

        if (entity.getStatus() != TaskStatus.IN_PROCESS
            || entity.getStatus() != TaskStatus.DONE
            || entity.getStatus() != TaskStatus.FAILED) {
            throw new IllegalArgumentException("Illegal argument in field \"status\"");
        }

        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    public TaskDtoResponse getOne(Long id, TaskDtoRequest request) {
        var found = repository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Task with id " + id + " not found"));
        return mapper.toDto(found);
    }

    public List<TaskDtoResponse> getAll() {
        List<TaskEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();


    }

    public TaskDtoResponse updateStatus(Long id, TaskDtoRequest request) {
        var found = repository.findById(id).
            orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
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
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        repository.delete(entity);
        return mapper.toDto(entity);




    }
}