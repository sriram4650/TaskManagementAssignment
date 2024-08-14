package com.scaler.taskmanagementassignment.Repositories;

import com.scaler.taskmanagementassignment.Entity.Priority;
import com.scaler.taskmanagementassignment.Entity.Status;
import com.scaler.taskmanagementassignment.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface Taskrepo extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByDueDate(LocalDate dueDate);

    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);

    List<Task> findByStatusAndPriorityAndDueDate(Status status, Priority priority, LocalDate dueDate);

    List<Task> findByStatusAndPriority(Status status, Priority priority);

    List<Task> findByStatusAndDueDate(Status status, LocalDate dueDate);

    List<Task> findByPriorityAndDueDate(Priority priority, LocalDate dueDate);



    // Add additional methods for combined filtering if needed
}
