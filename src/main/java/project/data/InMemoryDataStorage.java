package project.data;

import project.models.Task;
import project.models.User;

import java.util.List;
import java.util.Optional;

public class InMemoryDataStorage implements DataStorage {
    @Override
    public void addTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(long taskId) {

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
    public void deleteUser(long userId) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Optional<Task> getTaskById(long taskId) {
        return Optional.empty();
    }
}
