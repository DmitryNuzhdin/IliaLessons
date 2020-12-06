package project.services.console;

import project.exceptions.UserNotFoundException;
import project.models.Task;
import project.models.TaskData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleCommands {
    static List<ConsolePath> commands = new ArrayList<>();

    static {
        ConsolePath create = new ConsolePath(null,
                "Введите название задачи: ",
                s -> s.startsWith("create"),
                (model, user, lastCommands) -> {});

        ConsolePath createEnterName = new ConsolePath(create,
                "Введите задачу целиком: ",
                s -> true,
                (model, user, lastCommands) -> {});

        ConsolePath createFinish = new ConsolePath(createEnterName,
                "Создана задача: ",
                s -> true,
                (model, user, lastCommands) -> {
                    String taskText = lastCommands.pop();
                    String title = lastCommands.pop();
                    try {
                        Task task = model.createTask(user.getId(), new TaskData(title, taskText, false));
                        System.out.println("Создана задача: ");
                        System.out.println(task.toString());
                    } catch (UserNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        commands.add(create);
        commands.add(createEnterName);
        commands.add(createFinish);
    }
}
