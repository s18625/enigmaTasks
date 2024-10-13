package com.example.zadanie2.rest.service;

import com.example.zadanie2.api.model.TaskStatus;
import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.api.model.entity.UserEntity;
import com.example.zadanie2.rest.repo.TaskRepo;
import com.example.zadanie2.rest.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private TaskService taskService;

    private UUID taskId;
    private UUID userId;
    private TaskEntity taskEntity;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskId = UUID.randomUUID();
        userId = UUID.randomUUID();

        taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTitle("Sample Task");

        userEntity = new UserEntity();
        userEntity.setId(userId);
    }

    @Test
    void updateTaskStatus_successful() {
        // Arrange
        when(taskRepo.updateTaskStatusById(any(UUID.class), any(TaskStatus.class))).thenReturn(1);
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(taskEntity));

        // Act
        ResponseEntity<TaskEntity> response = taskService.updateTaskStatus(taskId, TaskStatus.COMPLETED);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskEntity, response.getBody());
    }

    @Test
    void updateTaskStatus_taskNotFound() {
        // Arrange
        when(taskRepo.updateTaskStatusById(any(UUID.class), any(TaskStatus.class))).thenReturn(0);

        // Act
        ResponseEntity<TaskEntity> response = taskService.updateTaskStatus(taskId, TaskStatus.COMPLETED);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void assignUsersToTask_successful() {
        // Arrange
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(taskEntity));
        when(userRepo.findAllById(anyList())).thenReturn(List.of(userEntity));
        when(taskRepo.save(taskEntity)).thenReturn(taskEntity);

        // Act
        ResponseEntity<TaskEntity> response = taskService.assignUsersToTask(taskId, List.of(userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskEntity, response.getBody());
    }

    @Test
    void assignUsersToTask_taskNotFound() {
        // Arrange
        when(taskRepo.findById(taskId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<TaskEntity> response = taskService.assignUsersToTask(taskId, List.of(userId));

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void assignUsersToTask_noUsersFound() {
        // Arrange
        when(taskRepo.findById(taskId)).thenReturn(Optional.of(taskEntity));
        when(userRepo.findAllById(anyList())).thenReturn(List.of());

        // Act
        ResponseEntity<TaskEntity> response = taskService.assignUsersToTask(taskId, List.of(userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
