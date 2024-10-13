package com.example.zadanie2.api.model;

/**
 * Enumeration representing the possible statuses of a task.
 *
 * <ul>
 *   <li>{@link #PENDING} - The task is created but not yet started.</li>
 *   <li>{@link #IN_PROGRESS} - The task is currently being worked on.</li>
 *   <li>{@link #COMPLETED} - The task has been completed.</li>
 * </ul>
 */
public enum TaskStatus {

    /**
     * The task is created but not yet started.
     */
    PENDING,

    /**
     * The task is currently being worked on.
     */
    IN_PROGRESS,

    /**
     * The task has been completed.
     */
    COMPLETED
}
