package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilia Moskalenko
 */
public interface DataStorage {
    Task addTask(long userId, TaskData task);
    Task updateTask(long userId, long taskId);
    void deleteTask (long userId, long taskId);
    List<Task> getAllActiveTask(long userId);
    List<Task> getAllTasks(long userId);
    User addUser(UserData user);
    User updateUser(long userId, UserData userData);
    void deleteUser(long userId);
    List<User> getAllUsers();
    Optional<TaskData> getTaskById(long userId, long taskId);
    Optional<UserData> getUser(long userId);
}
