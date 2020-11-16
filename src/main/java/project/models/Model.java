package project.models;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(UserData user) throws UserExistsException;
    Task createTask(long userId, TaskData task) throws UserNotFoundException;
    Task getTaskById(long taskId) throws TaskNotFoundException;
    Task updateTask(long userId, TaskData taskData) throws TaskNotFoundException;
    void deleteTask(long taskId) throws TaskNotFoundException;
    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;
    List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException;
}
