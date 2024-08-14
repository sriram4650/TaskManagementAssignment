package com.scaler.taskmanagementassignment.services;

import com.scaler.taskmanagementassignment.Entity.Priority;
import com.scaler.taskmanagementassignment.Entity.Status;
import com.scaler.taskmanagementassignment.Entity.Task;
import com.scaler.taskmanagementassignment.Repositories.Taskrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private final Taskrepo taskRepo;

    public TaskService(Taskrepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedAt(LocalDate.now());
        return taskRepo.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task updateTask(Long id, Task taskDetails) {
        return taskRepo.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setPriority(taskDetails.getPriority());
            task.setDueDate(taskDetails.getDueDate());
            task.setUpdatedAt(LocalDate.now());
            return taskRepo.save(task);
        }).orElse(null);
    }


    public List<Task> filterTasks(Status status, Priority priority, LocalDate dueDate) {
        if (status != null && priority != null && dueDate != null) {
            return taskRepo.findByStatusAndPriorityAndDueDate(status, priority, dueDate);
        } else if (status != null && priority != null) {
            return taskRepo.findByStatusAndPriority(status, priority);
        } else if (status != null && dueDate != null) {
            return taskRepo.findByStatusAndDueDate(status, dueDate);
        } else if (priority != null && dueDate != null) {
            return taskRepo.findByPriorityAndDueDate(priority, dueDate);
        } else if (status != null) {
            return taskRepo.findByStatus(status);
        } else if (priority != null) {
            return taskRepo.findByPriority(priority);
        } else if (dueDate != null) {
            return taskRepo.findByDueDate(dueDate);
        } else {
            return taskRepo.findAll();
        }
    }


    public List<Task> findtask(String search) {

        return taskRepo.findByTitleContainingOrDescriptionContaining(search, search);
    }

    public void DeleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}



