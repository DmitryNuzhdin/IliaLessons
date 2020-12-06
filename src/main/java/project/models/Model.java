package project.models;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(UserData user) throws UserExistsException;
    Task createTask(long userId, TaskData task) throws UserNotFoundException;
    Task getTaskById(long taskId) throws TaskNotFoundException;
    User getUserById(long userId) throws UserNotFoundException;
    Task updateTask(long userId, TaskData taskData) throws TaskNotFoundException;
    void deleteTask(long taskId) throws TaskNotFoundException;
    void deleteUser(long userId) throws UserNotFoundException;
    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;
    List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException;
}
