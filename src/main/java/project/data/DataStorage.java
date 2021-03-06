package project.data;

import project.models.Task;
import project.models.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilia Moskalenko
 */
public interface DataStorage {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask (long taskId);
    List<Task> getAllActiveTask(long userId);
    List<Task> getAllTasks(long userId);
    void addUser(User user);
    void deleteUser(long userId);
    List<User> getAllUsers();
    Optional<Task> getTaskById(long taskId);
}
