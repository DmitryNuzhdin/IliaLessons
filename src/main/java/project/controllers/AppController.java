package project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.exceptions.TaskNotFoundException;
import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private Model model;

    @PostMapping(value = "/users")
    User createUser(@RequestBody UserData user) throws UserExistsException {
        logger.debug("Received user: {}",user);
       return model.createUser(user);
    }

    @PostMapping(value = "user/{userId}/task")
    Task createTask(@PathVariable(value = "userId") Long userId, @RequestBody TaskData task) throws UserNotFoundException {
        return model.createTask(userId, task);
    }

    @GetMapping(value = "/task/{id}")
    Task getTaskById (@PathVariable(value = "id") Long id) throws TaskNotFoundException {
        logger.debug("Received task: {}", id);
        return model.getTaskById(id);
    }

    @GetMapping(value = "user/{id}")
    User getUserById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return model.getUserById(userId);
    }

    @PutMapping(value = "/task/{taskId}/update")
    Task updateTask(@PathVariable(value = "taskId") Long taskId,
                    @RequestBody TaskData taskData) throws TaskNotFoundException {
        return model.updateTask(taskId, taskData);
    }

    @DeleteMapping(value = "/task/{id}")
    void deleteTask(@PathVariable(value = "id") Long taskId) throws TaskNotFoundException{
        model.deleteTask(taskId);
    }

    @DeleteMapping(value = "user/{id}")
    void deleteUser(@PathVariable(value = "id") Long userId) throws UserNotFoundException{
        model.deleteUser(userId);
    }

    @GetMapping(value = "tasks/{userId}")
    List<Task> getAllTasksOfUser(@PathVariable(value = "userId") long userId) throws UserNotFoundException{
        return model.getAllTasksOfUser(userId);
    }

    @GetMapping(value = "tasks/active/{userId}")
    List<Task> getAllActiveTaskOfUser(@PathVariable(value = "userId") long userId) throws UserNotFoundException{
        return model.getAllActiveTaskOfUser(userId);
    }



}
