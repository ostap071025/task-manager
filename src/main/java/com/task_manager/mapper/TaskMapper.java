package com.task_manager.mapper;

import com.task_manager.api.dto.tasks.TaskDtoCreateRequest;
import com.task_manager.api.dto.tasks.TaskDtoResponse;
import com.task_manager.api.dto.tasks.TaskDtoUpdateRequest;
import com.task_manager.domain.Task.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskEntity toEntity(TaskDtoCreateRequest request) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setStatus(request.status());
        entity.setDeadline(request.deadline());
        entity.setPriority(request.priority());
        entity.setPriorityLevel(request.priority().getLevel());
        return entity;

    }
    public TaskDtoResponse toDto(TaskEntity entity) {
        TaskDtoResponse response = new TaskDtoResponse (
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getDeadline(),
                entity.getPriority(),
                entity.getUser().getId(),
                entity.getUser().getName()
        );
        return response;
    }


    public void updateEntity(TaskEntity entity, TaskDtoUpdateRequest request) {
        if (request.title() != null) {
            entity.setTitle(request.title());
        }
        if (request.description() != null) {
            entity.setDescription(request.description());
        }

        if (request.status() != null) {
            entity.setStatus(request.status());
        }

        if (request.deadline() != null) {
            entity.setDeadline(request.deadline());
        }

        if (request.priority() != null) {
            entity.setPriority(request.priority());
            entity.setPriorityLevel(request.priority().getLevel());
        }
    }
}
