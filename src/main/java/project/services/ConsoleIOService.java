package project.services;

import project.exeptions.TaskNotFoundException;
import project.exeptions.UserExistsException;
import project.exeptions.UserNotFoundException;
import project.models.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIOService implements IOService {
    private Model model;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("Введите имя: ");
                String name = bufferedReader.readLine();
                System.out.println("Введите фамилию: ");
                String secondName = bufferedReader.readLine();
                User user = model.createUser(new User(name, secondName));
                label:
                while (true) {
                    System.out.println("Введите \"help\" для списка команд");
                    String s = bufferedReader.readLine();

                    switch (s) {
                        case "create":
                            System.out.println("Введите название задачи: ");
                            String title = bufferedReader.readLine();
                            System.out.println("Введите задачу целиком: ");
                            String taskText = bufferedReader.readLine();
                            Task task = model.createTask(user.getId(), new Task(title, taskText));
                            System.out.println("Создана задача: ");
                            System.out.println(task.toString());
                            break;
                        case "update":
                            System.out.println("Введите id задачи которую хотите обновить: ");
                            long id = Long.parseLong(bufferedReader.readLine());
                            if (id>=model.getAllTasksOfUser(user.getId()).size()){
                                throw new TaskNotFoundException();
                            }
                            model.updateTask(id);
                            System.out.println("Задача id: "+ id + " обновлена.");
                            break;
                        case "active":
                            if (model.getAllActiveTaskOfUser(user.getId()).isEmpty()){
                                System.out.println("Список активных задач пуст");
                                break;
                            }
                            System.out.println("Список активных задач");
                            model.getAllActiveTaskOfUser(user.getId()).forEach(System.out::println);
                            break;
                        case "all":
                            if (model.getAllTasksOfUser(user.getId()).isEmpty()) {
                                System.out.println("Список задач пуст");
                                break;
                            }
                            System.out.println("Список всех задач");
                            model.getAllTasksOfUser(user.getId()).forEach(System.out::println);
                            break;
                        case "help":
                            System.out.println("Если хотите создать задачу введите: create\n" +
                                    "Если хотите обновить задачу введите: update\n" +
                                    "Если хотите просмотреть все активные задачи введите: active\n" +
                                    "Если хотите просмотреть все таски введите: all\n" +
                                    "Если хотите выйти из программы введите: exit\n");
                            break;
                        case "exit":
                            bufferedReader.close();
                            break label;
                    }
                }
            } catch (IOException | UserExistsException | UserNotFoundException | TaskNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    public ConsoleIOService(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        new Thread(runnable).start();
    }

    @Override
    public void stop() {

    }
}
