package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.List;
import java.util.Optional;

public class JDBCDataStorage implements DataStorage {
    @Override
    public Task addTask(long userId, TaskData task) {
        return null;
    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) {
        return null;
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
    public User addUser(UserData user) {
        return null;
    }

    @Override
    public User updateUser(long userId, UserData userData) {
        return null;
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

    @Override
    public Optional<User> getUser(long userId) {
        return Optional.empty();
    }
}
