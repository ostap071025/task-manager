package com.task_manager.domain.User;

import com.task_manager.domain.Task.TaskEntity;
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

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "joined_at")
    LocalDate joinedAt;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        joinedAt = LocalDate.now();
    }
}
