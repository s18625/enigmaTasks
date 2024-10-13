package com.example.zadanie2.rest.controller;

import com.example.zadanie2.api.model.TaskStatus;
import com.example.zadanie2.rest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for handling task-related operations such as updating the task status
 * and assigning users to a task.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/taskEntities")
public class TaskController {

    private final TaskService taskService;

    /**
     * Endpoint to update the status of a task.
     *
     * @param id the UUID of the task to update
     * @param status the new status to be set for the task
     * @return a {@link ResponseEntity} with the updated task or an error response
     */
    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable UUID id,
            @PathVariable TaskStatus status) {
        return taskService.updateTaskStatus(id, status);
    }

    /**
     * Endpoint to assign a list of users to a task.
     *
     * @param taskId the UUID of the task to which users will be assigned
     * @param userIds a list of UUIDs representing the users to be assigned to the task
     * @return a {@link ResponseEntity} with the updated task or an error response
     */
    @PostMapping("/{taskId}/assignUsers")
    public ResponseEntity<?> assignUsersToTask(
            @PathVariable UUID taskId,
            @RequestBody List<UUID> userIds) {
        return taskService.assignUsersToTask(taskId, userIds);
    }
}
