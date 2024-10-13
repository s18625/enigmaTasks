package com.example.zadanie2.rest.repo;

import com.example.zadanie2.api.model.entity.TaskEntity;
import com.example.zadanie2.api.model.TaskStatus;
import com.example.zadanie2.api.model.entity.UserEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
public class DataLoader {

    @Bean
    ApplicationRunner init(UserRepo userRepository, TaskRepo taskRepository) {
        return args -> {
            UserEntity user1 = new UserEntity();
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setEmail("john.doe@example.com");

            UserEntity user2 = new UserEntity();
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setEmail("jane.smith@example.com");

            UserEntity user3 = new UserEntity();
            user3.setFirstName("Alice");
            user3.setLastName("Johnson");
            user3.setEmail("alice.johnson@example.com");

            userRepository.saveAll(List.of(user1, user2, user3));

            // Creating some tasks
            TaskEntity task1 = new TaskEntity();
            task1.setTitle("Complete project");
            task1.setDescription("Finish the project by the end of the week");
            task1.setStatus(TaskStatus.PENDING);
            task1.setDeadline(LocalDate.of(2024, 10, 31));
            task1.setAssignedUsers(List.of(user1, user2));

            TaskEntity task2 = new TaskEntity();
            task2.setTitle("Prepare report");
            task2.setDescription("Prepare the monthly performance report");
            task2.setStatus(TaskStatus.IN_PROGRESS);
            task2.setDeadline(LocalDate.of(2024, 11, 5));
            task2.setAssignedUsers(List.of(user3));

            TaskEntity task3 = new TaskEntity();
            task3.setTitle("Team meeting");
            task3.setDescription("Hold a meeting to discuss the new features");
            task3.setStatus(TaskStatus.COMPLETED);
            task3.setDeadline(LocalDate.of(2024, 10, 15));
            task3.setAssignedUsers(List.of(user1));

            taskRepository.saveAll(List.of(task1, task2, task3));

            System.out.println("Sample data inserted.");
        };
    }
}
