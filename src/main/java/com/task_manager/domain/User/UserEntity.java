package com.task_manager.domain.User;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.task_manager.domain.Task.TaskEntity;
import com.task_manager.domain.authentication.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "joined_at")
    LocalDate joinedAt;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @PrePersist
    public void prePersist() {
        joinedAt = LocalDate.now();
    }
}
