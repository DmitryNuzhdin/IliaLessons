package project.models;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(UserData user) throws UserExistsException;
    Task createTask(long userId, TaskData task) throws UserNotFoundException;
    Task updateTask(long taskId) throws TaskNotFoundException;
    void deleteTask(long userId, long taskId) throws UserNotFoundException, TaskNotFoundException;
    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;
    List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException;
}
