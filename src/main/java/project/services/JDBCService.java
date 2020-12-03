package project.services;

import project.exceptions.UserExistsException;
import project.exceptions.UserNotFoundException;
import project.models.Model;
import project.models.Task;
import project.models.User;
import project.models.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * @author Ilia Moskalenko
 */
public class JDBCService implements IOService {
    private Model model;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String name;
                    String secondName;
                    do {
                        System.out.println("Введите имя: ");
                        name = bufferedReader.readLine();
                    } while (name.trim().isEmpty());
                    do {
                        System.out.println("Введите фамилию: ");
                        secondName = bufferedReader.readLine();
                    } while (secondName.trim().isEmpty());
                    User user = model.createUser(new UserData(name, secondName));
                    System.out.println(user);

                model.createTask(1, new Task(1,1,"t1","task1", false));


            } catch (UserNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (UserExistsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    };

    public JDBCService (Model model){
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
