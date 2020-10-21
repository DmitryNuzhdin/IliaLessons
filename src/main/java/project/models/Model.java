package project.models;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser(User user) throws UserExistsException;
    Task createTask(long userId, Task task) throws UserNotFoundException;
    void updateTask(Task task) throws TaskNotFoundException;
    List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException;
}

class UserNotFoundException extends Exception{};
class UserExistsException extends Exception{};
class TaskNotFoundException extends Exception{};