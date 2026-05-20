package com.task_manager.repository;

import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.Task.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findAllByUser_Id(Long userId, Pageable pageable);

    List<TaskEntity> findAllByUser_Id(Long userId);

    @Query("select t from TaskEntity t where t.status = :status")
    Page<TaskEntity> findByStatus(TaskStatus status, Pageable pageable);
}


