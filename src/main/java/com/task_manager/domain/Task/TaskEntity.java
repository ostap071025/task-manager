package com.task_manager.domain.Task;

import com.task_manager.domain.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class TaskEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    TaskStatus status;

    @Column (name = "createdAt", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "deadline")
    LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    TaskPriority priority;

    private int priorityLevel;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @PrePersist
    void setNow() {
        this.createdAt = LocalDateTime.now();
    }
}
