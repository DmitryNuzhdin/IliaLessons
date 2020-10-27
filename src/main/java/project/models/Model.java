package project.models;

import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(User user) throws UserExistsException;
    Task createTask(long userId, Task task) throws UserNotFoundException;
    void updateTask(long taskId) throws TaskNotFoundException;
    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;
    List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException;
}
