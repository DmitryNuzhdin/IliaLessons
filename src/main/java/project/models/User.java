package project.models;

import java.util.List;
import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class User {
    private int id;
    private String name;
    private String secondName;
    private List<Task> tasksList;

    public User(int id, String name, String secondName) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(tasksList, user.tasksList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, tasksList);
    }

    @Override
    public String toString() {
        return "User:" + id +
                ", name: " + name  +
                ", secondName: " + secondName;
    }
}
