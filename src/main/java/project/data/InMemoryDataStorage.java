package project.data;

import org.springframework.stereotype.Component;
import project.exceptions.UserNotFoundException;
import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryDataStorage implements DataStorage {
    private static long countUser;
    Map<Long, User> userList = new HashMap<>();
    private static long countTask;
    Map<Long, Task> taskList = new HashMap<>();
    Map<Long, List<Long>> usersTasksMap = new HashMap<>();



    @Override
    public Task addTask(long userId, TaskData taskData) {
        countTask++;
        taskList.put(countTask, new Task(userId, countTask, taskData.getTitle(), taskData.getFullTaskText(), taskData.isSolved()));
        List<Long> userTasks = usersTasksMap.computeIfAbsent(userId, k -> new ArrayList<>());
        userTasks.add(countTask);

        return taskList.get(countTask);

    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) {
        Task oldTask = taskList.get(taskId);
        Task newTask = new Task(oldTask.getUserId(),
                oldTask.getId(),
                taskData.getTitle(),
                taskData.getFullTaskText(),
                taskData.isSolved());
        taskList.put(taskId, newTask);

        return taskList.get(taskId);
    }

    @Override
    public Optional<Task> getTaskById(long taskId)  {
        if (taskList.containsKey(taskId)) return Optional.of(taskList.get(taskId));
        return Optional.empty();
    }

    @Override
    public void deleteTask(long taskId) {
       taskList.remove(taskId);
       usersTasksMap.forEach((userIds, taskIds) -> taskIds.remove(taskId)); //TODO: it should not be O(n)
    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        List<Task> allActiveTasksOfUser = new ArrayList<>();
        for (Long ids : usersTasksMap.get(userId)){
            if (!taskList.get(ids).isSolved()){
                allActiveTasksOfUser.add(taskList.get(ids));
            }
        }
        return allActiveTasksOfUser;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        List<Task> allTasksOfUser = new ArrayList<>();
        for (Long ids : usersTasksMap.get(userId)){
            allTasksOfUser.add(taskList.get(ids));
        }
         return allTasksOfUser;
    }

    @Override
    public User addUser(UserData userData) {
        countUser++;
        userList.put(countUser, new User(countUser, userData.getName(), userData.getSecondName()));
        return userList.get(countUser);
    }

    @Override
    public User updateUser(long userId, UserData userData) {
        userList.put(userId, new User(userId,
                userData.getName(),
                userData.getSecondName()));
        return userList.get(userId);
    }

    @Override
    public void deleteUser(long userId) {
        userList.remove(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        for (Map.Entry<Long, User> pair : userList.entrySet()){
            allUsers.add(pair.getValue());
        }
        return allUsers;
    }


    @Override
    public Optional<User> getUser(long userId) {
        if (userList.containsKey(userId)) return Optional.of(userList.get((userId)));
        return Optional.empty();
    }
}
