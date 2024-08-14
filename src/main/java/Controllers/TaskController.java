package Controllers;

import com.scaler.taskmanagementassignment.Entity.Priority;
import com.scaler.taskmanagementassignment.Entity.Status;
import com.scaler.taskmanagementassignment.Entity.Task;
import com.scaler.taskmanagementassignment.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/tasks")

public class TaskController {
    private final TaskService taskService;

    public TaskController(@Qualifier("task service") TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }
    public List<Task> getAlltasks(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String dueDate,
            @RequestParam(required = false) String search
    ){
        if(search != null){
            return taskService.findtask(search);
        }
        return taskService.filterTasks(
                status != null ? Status.valueOf(status.toUpperCase()) : null,
                priority != null ? Priority.valueOf(priority.toUpperCase()) : null,
                dueDate != null ? LocalDate.parse(dueDate) : null
        );
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.DeleteTask(taskId);
    }
}
