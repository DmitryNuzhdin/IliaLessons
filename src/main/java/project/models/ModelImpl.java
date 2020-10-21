package project.models;

import project.data.DataStorage;

import java.util.List;

public class ModelImpl implements Model {
    private DataStorage dataStorage;

    public ModelImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public User createUser(User user) throws UserExistsException {
        return null;
    }

    @Override
    public Task createTask(long userId, Task task) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateTask(Task task) throws TaskNotFoundException {
        if (!dataStorage.getTaskById(task.getId()).isPresent()) throw new TaskNotFoundException();
        dataStorage.updateTask(task);
    }

    @Override
    public List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException {
        return null;
    }
}
