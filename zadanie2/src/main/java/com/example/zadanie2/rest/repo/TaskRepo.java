package com.example.zadanie2.rest.repo;

import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.api.model.dto.TaskEntityDto;
import com.example.zadanie2.api.model.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link TaskEntity}, providing access to task-related
 * data. This interface extends {@link JpaRepository} to provide basic CRUD operations
 * and includes custom queries for additional operations.
 *
 * The repository uses Spring Data REST to expose these operations as REST endpoints.
 * The default projection is {@link TaskEntityDto}.
 */
@RepositoryRestResource(excerptProjection = TaskEntityDto.class)
public interface TaskRepo extends JpaRepository<TaskEntity, UUID> {

    /**
     * Finds tasks whose titles contain the given string, ignoring case.
     * This query is exposed as a REST endpoint at `/taskEntities/search/byTitle`.
     *
     * @param title the string to search for in task titles
     * @return a list of tasks whose titles contain the given string
     */
    @RestResource(path = "byTitle")
    List<TaskEntity> findByTitleContainingIgnoreCase(@Param("title") String title);

    /**
     * Finds tasks by their status.
     * This query is exposed as a REST endpoint at `/taskEntities/search/byStatus`.
     *
     * @param status the status of the tasks to search for
     * @return a list of tasks with the given status
     */
    @RestResource(path = "byStatus")
    List<TaskEntity> findByStatus(@Param("status") TaskStatus status);

    /**
     * Finds tasks by their deadline.
     * This query is exposed as a REST endpoint at `/taskEntities/search/byDeadline`.
     *
     * @param deadline the deadline date to search for
     * @return a list of tasks with the given deadline
     */
    @RestResource(path = "byDeadline")
    List<TaskEntity> findByDeadline(@Param("deadline") LocalDate deadline);

    /**
     * Updates the status of a task by its ID.
     * This query is transactional and modifies the task status in the database.
     *
     * @param id the UUID of the task to update
     * @param status the new status to set for the task
     * @return the number of rows affected
     */
    @Modifying
    @Transactional
    @Query("UPDATE TaskEntity t SET t.status = :status WHERE t.id = :id")
    int updateTaskStatusById(@Param("id") UUID id, @Param("status") TaskStatus status);
}
