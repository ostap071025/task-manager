package com.task_manager.repository;

import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
