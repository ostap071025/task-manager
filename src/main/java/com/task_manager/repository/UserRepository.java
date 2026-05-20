package com.task_manager.repository;

import com.task_manager.api.dto.users.UserUpdateRequestDto;
import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //    @Query("select u from UserEntity u left join fetch u.tasks where u.id = :id")
    @Query("select u from UserEntity u left join fetch u.tasks where u.id = :id")
    Optional<UserEntity> findByIdWithTasks(Long id);

    boolean existsByEmail(String email);
    boolean existsByPassword(String password);

    @Query("select u from UserEntity u where u.email = :email")
    Page<UserEntity> findByEmail(String email, Pageable pageable);


    @Query("select u from UserEntity u where u.name = :name")
    Page<UserEntity> findByName(String name, Pageable pageable);


    @Query("select u from UserEntity u where u.tasks is not empty")
    Page<UserEntity> findWhoHaveTasks(Pageable pageable);



    Optional<UserEntity> findByEmail(String email);





}








