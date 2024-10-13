package com.example.zadanie2.rest.service;

import com.example.zadanie2.api.model.TaskStatus;
import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.api.model.entity.UserEntity;
import com.example.zadanie2.rest.repo.TaskRepo;
import com.example.zadanie2.rest.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class that handles business logic related to tasks, such as updating the status
 * of a task and assigning users to tasks.
 */
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    /**
     * Updates the status of a task identified by the provided UUID.
     *
     * @param id the UUID of the task
     * @param status the new status to be assigned to the task
     * @return a {@link ResponseEntity} containing the updated {@link TaskEntity} if found,
     *         otherwise {@link HttpStatus#NOT_FOUND}
     */
    @Transactional
    public ResponseEntity<TaskEntity> updateTaskStatus(UUID id, TaskStatus status) {
        int updatedRows = taskRepo.updateTaskStatusById(id, status);
        if (updatedRows > 0) {
            Optional<TaskEntity> updatedTask = taskRepo.findById(id);
            return updatedTask
                    .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Assigns a list of users to a task identified by the provided UUID.
     *
     * @param taskId the UUID of the task to which the users will be assigned
     * @param userIds a list of UUIDs representing the users to be assigned to the task
     * @return a {@link ResponseEntity} containing the updated {@link TaskEntity} if successful,
     *         or an error status if the task or users cannot be found
     */
    public ResponseEntity<TaskEntity> assignUsersToTask(UUID taskId, List<UUID> userIds) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(taskId);

        if (taskOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TaskEntity task = taskOptional.get();
        List<UserEntity> users = userRepo.findAllById(userIds);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        task.setAssignedUsers(users);

        return new ResponseEntity<>(taskRepo.save(task), HttpStatus.OK);
    }
}
