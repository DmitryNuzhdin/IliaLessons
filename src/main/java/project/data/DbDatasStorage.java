package project.data;

import project.models.Task;
import project.models.User;

import java.util.List;

/**
 * @author Ilia Moskalenko
 */
public class DbDatasStorage implements DataStorage{
    @Override
    public void addTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        return null;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
