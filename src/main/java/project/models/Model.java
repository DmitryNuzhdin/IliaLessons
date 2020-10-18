package project.models;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public interface Model {
    User createUser();
    Task createTask();
    Task updateTask(Task task);
}
