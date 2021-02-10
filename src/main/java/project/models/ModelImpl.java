package project.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.data.DataStorage;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Objects;


@Component
public class ModelImpl implements Model {
    private DataStorage dataStorage;

    @Autowired
    public ModelImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public User createUser(UserData user) throws UserExistsException {
        boolean userNameExists = dataStorage.getAllUsers().stream()
                .anyMatch(user1 -> Objects.equals(user1.getName(), user.getName()));
        if (userNameExists) throw new UserExistsException();
        return dataStorage.addUser(user);
    }

    @Override
    public Task createTask(long userId, TaskData task) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.addTask(userId, task);

    }

    @Override
    public Task getTaskById(long taskId) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        return dataStorage.getTaskById(taskId).get();
    }

    @Override
    public User getUserById(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getUser(userId).get();
    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        return dataStorage.updateTask(taskId, taskData);
    }

    @Override
    public void deleteTask(long taskId) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        dataStorage.deleteTask(taskId);
    }

    @Override
    public void deleteUser(long userId) throws UserNotFoundException {
        dataStorage.deleteUser(userId);
    }

    @Override
    public List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllTasks(userId);
    }

    @Override
    public List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllActiveTask(userId);
    }


}
