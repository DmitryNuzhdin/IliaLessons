package project.data;

import project.models.Task;
import project.models.User;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface DataStorage {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask (Task task);
    List<Task> getAllActiveTask(long userId);
    List<Task> getAllTasks(long userId);
    void addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
}
