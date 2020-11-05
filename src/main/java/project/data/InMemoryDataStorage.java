package project.data;

import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.*;

public class InMemoryDataStorage implements DataStorage {
    private static long countUser;
    Map<Long, UserData> userList = new HashMap<>();
    private static long countTask;
    Map<Long, TaskData> taskList = new HashMap<>();
    Map<Long, Map<Long, TaskData>> usersTasksMap = new HashMap<>();



    @Override
    public Task addTask(long userId, TaskData task) {
        countTask++;
        taskList.put(countTask, task);
        usersTasksMap.put(userId, taskList);
        return new Task(countTask, task.getTitle(), task.getFullTaskText(), task.isSolved());

    }

    @Override
    public Task updateTask(long taskId) {
        TaskData task = taskList.get(taskId).solvedTask(true);
        taskList.put(taskId, task);
        usersTasksMap.put(taskId, taskList);
        return new Task(taskId, taskList.get(taskId).getTitle(), taskList.get(taskId).getFullTaskText(), taskList.get(taskId).isSolved());
    }

    @Override
    public Optional<TaskData> getTaskById(long taskId) {
        if (!taskList.isEmpty()){
            if (taskList.containsKey(taskId)) return Optional.of(taskList.get(taskId));
        }
        return Optional.empty();
    }

    @Override
    public void deleteTask(long userId, long taskId) {
       taskList.remove(taskId);
       usersTasksMap.put(userId, taskList);
    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        List<Task>  active = new ArrayList<>();
        for (Map.Entry<Long, TaskData> pair : usersTasksMap.get(userId).entrySet()){
            if (!pair.getValue().isSolved()) active.add(new Task(pair.getKey(),
                    pair.getValue().getTitle(),
                    pair.getValue().getFullTaskText(),
                    pair.getValue().isSolved()));
        }
        return active;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        List<Task> all = new ArrayList<>();
        for (Map.Entry<Long, TaskData> pair : usersTasksMap.get(userId).entrySet()){
            all.add(new Task(pair.getKey(),
                    pair.getValue().getTitle(),
                    pair.getValue().getFullTaskText(),
                    pair.getValue().isSolved()));
        }
         return all;
    }

    @Override
    public User addUser(UserData user) {
        countUser++;
        userList.put(countUser, user);
        return new User(countUser, userList.get(countUser).getName(),userList.get(countUser).getSecondName());
    }

    @Override
    public User updateUser(long userId, String name, String secondName) {
        userList.put(userId, userList.get(userId).update(name,secondName));
        return new User(userId, name, secondName);
    }

    @Override
    public void deleteUser(long userId) {
        userList.remove(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        for (Map.Entry<Long, UserData> pair : userList.entrySet()){
            allUsers.add(new User(pair.getKey(),
                    pair.getValue().getName(),
                    pair.getValue().getSecondName()));
        }
        return allUsers;
    }


    @Override
    public Optional<UserData> getUser(long userId) {
        if (!userList.isEmpty()){
            if (userList.containsKey(userId)) return Optional.of(userList.get((userId)));
        }
        return Optional.empty();
    }
}
