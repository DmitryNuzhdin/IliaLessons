package project.data;

import project.models.Task;
import project.models.User;

import java.util.*;

public class InMemoryDataStorage implements DataStorage {
   // Map<Long, Map<Long,Task>> map = new HashMap<>();
    List<User> userList = new ArrayList<>();
    List<Task> taskList = new ArrayList<>();



    @Override
    public void addTask(Task task) {
        taskList.add(task);
        task.setId(taskList.indexOf(task));
    }

    @Override
    public void updateTask(long taskId) {
        taskList.get((int) taskId).setSolved(true);
    }

    @Override
    public void deleteTask(long taskId) {
    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        List<Task> active = new ArrayList<>();
        for (Task task : taskList){
            if (!task.isSolved()) active.add(task);
        }
        return active;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        return taskList;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
        user.setId(userList.indexOf(user));
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public Optional<Task> getTaskById(long taskId) {
        if (!taskList.isEmpty()){
            if (taskList.contains(taskList.get((int) taskId))) return Optional.of(taskList.get((int) taskId));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(long userId) {
        if (!userList.isEmpty()){
            if (userList.contains(userList.get((int) userId))) return Optional.of(userList.get((int) userId));
        }
        return Optional.empty();
    }
}
