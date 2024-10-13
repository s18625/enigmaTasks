package com.example.zadanie2.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Represents a user entity in the system.
 * This entity holds information about a user, such as their first name, last name, email,
 * and the tasks they are assigned to.
 *
 * The entity is mapped to a database table using JPA annotations.
 */
@Entity
@Data
public class UserEntity {

    /**
     * The unique identifier (UUID) of the user.
     * This ID is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The first name of the user.
     * This field is mandatory and cannot be blank.
     * The name must be between 2 and 100 characters.
     */
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String firstName;

    /**
     * The last name of the user.
     * This field is mandatory and cannot be blank.
     * The last name must be between 2 and 100 characters.
     */
    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    /**
     * The email address of the user.
     * This field is validated for proper email format.
     */
    @Email(message = "Invalid email format")
    private String email;

    /**
     * A list of tasks assigned to this user.
     * The relationship is many-to-many, and this side of the relationship is the inverse of the task's assigned users.
     * The {@link JsonBackReference} annotation is used to prevent circular references during JSON serialization.
     */
    @JsonBackReference
    @ManyToMany(mappedBy = "assignedUsers")
    private List<TaskEntity> tasks;
}
