package com.task_manager.domain;

import com.task_manager.api.TaskDtoRequest;
import com.task_manager.api.TaskDtoResponse;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.EndElement;
import java.util.concurrent.locks.ReadWriteLock;

@Component
public class TaskMapper {
    public TaskEntity toEntity(TaskDtoRequest request) {
        TaskEntity entity = new TaskEntity();
        entity.setTitle(request.title());
        entity.setDescription(request.description());
        entity.setStatus(request.status());
        entity.setDeadline(request.deadline());
        return entity;

    }
    public TaskDtoResponse toDto(TaskEntity entity) {
        TaskDtoResponse response = new TaskDtoResponse (
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getDeadline()
        );
        return response;
    }
}
