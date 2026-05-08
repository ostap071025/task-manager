package com.task_manager.repository;

import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

//    @Query("select u from UserEntity u left join fetch u.tasks where u.id = :id")
    @Query(
        value = """
            SELECT *
            from users u
            left join tasks t
            on t.user_id = u.id
            where u.id = :id
            """,
        nativeQuery = true
    )
    Optional<UserEntity> findByIdWithTasks(Long id);
}


