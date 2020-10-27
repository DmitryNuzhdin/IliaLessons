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
      //  if (dataStorage.getUser(user.getId()).isPresent()) throw new UserExistsException();
        dataStorage.addUser(user);
        return user;
    }

    @Override
    public Task createTask(long userId, Task task) throws UserNotFoundException {
       // if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        dataStorage.addTask(task);
        return task;
    }

    @Override
    public void updateTask(long taskId) throws TaskNotFoundException {
        //if (!dataStorage.getTaskById(taskId).isPresent()) throw new TaskNotFoundException();
        dataStorage.updateTask(taskId);
    }

    @Override
    public List<Task> getAllTasksOfUser(long userId) throws UserNotFoundException {
        //if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllTasks(userId);
    }

    @Override
    public List<Task> getAllActiveTaskOfUser(long userId) throws UserNotFoundException {
       // if (!dataStorage.getUser(userId).isPresent()) throw new UserNotFoundException();
        return dataStorage.getAllActiveTask(userId);
    }


}
