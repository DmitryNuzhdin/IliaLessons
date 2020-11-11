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
    public Task addTask(long userId, TaskData task) {
        countTask++;
        taskList.put(countTask, task);
        List<Long> usersTasks = usersTasksMap.computeIfAbsent(userId, k -> new ArrayList<>());
        usersTasks.add(countTask);
        return new Task(countTask, task.getTitle(), task.getFullTaskText(), task.isSolved());
    }

    @Override
    public Task updateTask(long taskId) {
        TaskData newTaskData = taskList.get(taskId).solvedTask(true);
        taskList.put(taskId, newTaskData);
        return new Task(taskId, newTaskData.getTitle(),
                newTaskData.getFullTaskText(),
                newTaskData.isSolved());
    }

    @Override
    public Optional<TaskData> getTaskById(long taskId)  {
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

        List<Long> userTaskIds = usersTasksMap.get(userId);
        if (userTaskIds != null) {
            return userTaskIds.stream().map(taskId -> getTaskById(taskId).get())
                    .filter(....)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>(0);
        }
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
    public User updateUser(long userId, UserData userData) {
        userList.put(userId, userData);
        return new User(userId, userData.getName(), userData.getSecondName());
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
