package project.models;

import org.springframework.stereotype.Component;
import project.data.DataStorage;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;

import java.util.List;

@Component
public class ModelImpl implements Model {
    private DataStorage dataStorage;

    public ModelImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public User createUser(UserData user) throws UserExistsException {
        if (dataStorage.getUser(dataStorage.addUser(user).getId()).isPresent()) throw new UserExistsException();
        return dataStorage.addUser(user);
    }

    @Override
    public Task createTask(long userId, TaskData task) throws UserNotFoundException {
        if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.addTask(userId, task);

    }

    @Override
    public Task updateTask(long taskId) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        return dataStorage.updateTask(taskId);
    }

    @Override
    public void deleteTask(long taskId) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        dataStorage.deleteTask(taskId);
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
