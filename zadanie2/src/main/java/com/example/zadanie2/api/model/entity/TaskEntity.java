package com.example.zadanie2.api.model.entity;

import com.example.zadanie2.api.model.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Represents a task entity in the system.
 * This entity holds information about a task, such as its title, description, status,
 * deadline, and assigned users.
 *
 * The entity is mapped to a database table using JPA annotations.
 */
@Entity
@Data
public class TaskEntity {

    /**
     * The unique identifier (UUID) of the task.
     * This ID is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The title of the task.
     * This field is mandatory and cannot be blank.
     */
    @NotBlank(message = "Title cannot be blank")
    private String title;

    /**
     * A brief description of the task.
     * This field is optional and can be left empty.
     */
    private String description;

    /**
     * The current status of the task.
     * Stored as an enumerated type (TaskStatus) in the database.
     */
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /**
     * The deadline for completing the task.
     * This field is represented as a LocalDate.
     */
    private LocalDate deadline;

    /**
     * A list of users assigned to this task.
     * The relationship is many-to-many, with a join table `task_user` that links tasks and users.
     */
    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> assignedUsers;
}
