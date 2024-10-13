package com.example.zadanie2.api.model.dto;

import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.api.model.TaskStatus;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.UUID;

/**
 * A projection interface for the {@link TaskEntity} entity.
 * This projection exposes a subset of the {@link TaskEntity}'s fields,
 * providing access to its ID, title, description, status, and deadline.
 *
 * The projection is named "withId" and can be used in Spring Data REST
 * to project the selected fields of a {@link TaskEntity} in the responses.
 */
@Projection(name = "withId", types = { TaskEntity.class })
public interface TaskEntityDto {

    /**
     * Returns the unique identifier (UUID) of the task.
     *
     * @return the UUID of the task
     */
    UUID getId();

    /**
     * Returns the title of the task.
     *
     * @return the title of the task
     */
    String getTitle();

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    String getDescription();

    /**
     * Returns the current status of the task.
     *
     * @return the status of the task
     */
    TaskStatus getStatus();

    /**
     * Returns the deadline of the task.
     *
     * @return the deadline of the task as a {@link LocalDate}
     */
    LocalDate getDeadline();
}
