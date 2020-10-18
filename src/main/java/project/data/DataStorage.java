package project.data;

import project.models.Task;
import project.models.User;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface DataStorage {
    void addTask(Task task);
    void deleteTask (Task task);
    List<Task> getAllTasks();
}
