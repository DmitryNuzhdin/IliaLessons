package project.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.models.Task;
import project.models.TaskData;
import project.models.User;
import project.models.UserData;
import project.models.entity.TaskEntity;
import project.models.entity.TaskRepo;
import project.models.entity.UserEntity;
import project.models.entity.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JpaDataStorage implements DataStorage{
    private static final Logger log = LoggerFactory.getLogger(JpaDataStorage.class);
    private UserRepo userRepo;
    private TaskRepo taskRepo;

    @Autowired
    public JpaDataStorage(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public Task addTask(long userId, TaskData task) {
        Optional<UserEntity> userEntityOpt = userRepo.findById(userId);
        UserEntity userEntity = userEntityOpt.get();
        TaskEntity taskEntity = new TaskEntity(userEntity, task);
        taskRepo.save(taskEntity);
        return new Task( userEntity.getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(),
                taskEntity.getFullTaskText(), taskEntity.isSolved());
    }

    @Override
    public Task updateTask(long taskId, TaskData taskData) {
        Optional<TaskEntity> taskEntityOpt = taskRepo.findById(taskId);
        TaskEntity taskEntity = taskEntityOpt.get();
        taskEntity.setTitle(taskData.getTitle());
        taskEntity.setFullTaskText(taskData.getFullTaskText());
        taskEntity.setSolved(taskData.isSolved());
        taskRepo.save(taskEntity);
        return new Task( taskEntity.getUserEntity().getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(),
                taskEntity.getFullTaskText(), taskEntity.isSolved());
    }

    @Override
    public void deleteTask(long taskId) {
        taskRepo.deleteById(taskId);
    }

    @Override
    public List<Task> getAllActiveTask(long userId) {
        List<Task> taskList = new ArrayList<>();
        List<TaskEntity> taskEntityList = (List<TaskEntity>) taskRepo.findAll();
        taskEntityList.stream().map(taskEntity -> taskList.add(new Task(
                taskEntity.getUserEntity().getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(), taskEntity.getFullTaskText(),
                taskEntity.isSolved())));
        return taskList;
    }

    @Override
    public List<Task> getAllTasks(long userId) {
        boolean active = true;
        List<Task> taskList = new ArrayList<>();
        List<TaskEntity> taskEntityList = taskRepo.findUserIdAndIsSolved(userId,active);
        taskEntityList.stream().map(taskEntity -> taskList.add(new Task(
                taskEntity.getUserEntity().getUserId(), taskEntity.getTaskId(), taskEntity.getTitle(), taskEntity.getFullTaskText(),
                taskEntity.isSolved())));
        return taskList;
    }

    @Override
    public User addUser(UserData user) {
        UserEntity userEntity = new UserEntity(user);
        userRepo.save(userEntity);
        return new User(userEntity.getUserId(), userEntity.getName(), userEntity.getSecondName());
    }

    @Override
    public User updateUser(long userId, UserData userData) {
        Optional<UserEntity> userEntityOpt = userRepo.findById(userId);
        UserEntity userEntity = userEntityOpt.get();
        userEntity.setName(userData.getName());
        userEntity.setSecondName(userData.getSecondName());
        return new User(userEntity.getUserId(), userEntity.getName(), userEntity.getSecondName());
    }

    @Override
    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        List<UserEntity> userEntities = (List<UserEntity>) userRepo.findAll();
        userEntities.stream().map(userEntity -> userList.add(new User(userEntity.getUserId(),
                userEntity.getName(), userEntity.getSecondName())));
        return userList;
    }

    @Override
    public Optional<Task> getTaskById(long taskId) {
        Optional<TaskEntity> taskEntityOpt = taskRepo.findById(taskId);
        TaskEntity taskEntity = taskEntityOpt.get();

        return Optional.of(new Task(taskEntity.getUserEntity().getUserId(), taskEntity.getTaskId(),
                taskEntity.getTitle(), taskEntity.getFullTaskText(), taskEntity.isSolved()));
    }

    @Override
    public Optional<User> getUser(long userId) {
        Optional<UserEntity> userEntityOpt = userRepo.findById(userId);
        UserEntity userEntity = userEntityOpt.get();
        return Optional.of(new User(userEntity.getUserId(), userEntity.getName(), userEntity.getSecondName()));
    }
}
