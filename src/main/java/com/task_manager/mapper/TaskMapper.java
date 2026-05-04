package com.task_manager.mapper;

import com.task_manager.api.dto.TaskDtoRequest;
import com.task_manager.api.dto.TaskDtoResponse;
import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.User.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskEntity toEntity(TaskDtoRequest request) {
        UserEntity entityUser = new UserEntity();
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
                entity.getUser().getId()
        );
        return response;
    }
}
