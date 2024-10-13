package com.example.zadanie2.rest.controller;

import com.example.zadanie2.api.model.TaskStatus;
import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.rest.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private UUID taskId;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskId = UUID.randomUUID();
        userId = UUID.randomUUID();
    }

    @Test
    void updateTaskStatus_successful() {
        // Arrange
        ResponseEntity<TaskEntity> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        when(taskService.updateTaskStatus(any(UUID.class), any(TaskStatus.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = taskController.updateTaskStatus(taskId, TaskStatus.COMPLETED);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).updateTaskStatus(taskId, TaskStatus.COMPLETED);
    }

    @Test
    void updateTaskStatus_taskNotFound() {
        // Arrange
        ResponseEntity<TaskEntity> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(taskService.updateTaskStatus(any(UUID.class), any(TaskStatus.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = taskController.updateTaskStatus(taskId, TaskStatus.COMPLETED);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskService, times(1)).updateTaskStatus(taskId, TaskStatus.COMPLETED);
    }

    @Test
    void assignUsersToTask_successful() {
        // Arrange
        ResponseEntity<TaskEntity> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        when(taskService.assignUsersToTask(any(UUID.class), anyList())).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = taskController.assignUsersToTask(taskId, List.of(userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).assignUsersToTask(taskId, List.of(userId));
    }

    @Test
    void assignUsersToTask_taskNotFound() {
        // Arrange
        ResponseEntity<TaskEntity> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(taskService.assignUsersToTask(any(UUID.class), anyList())).thenReturn(expectedResponse);

        // Act
        ResponseEntity<?> response = taskController.assignUsersToTask(taskId, List.of(userId));

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskService, times(1)).assignUsersToTask(taskId, List.of(userId));
    }
}
