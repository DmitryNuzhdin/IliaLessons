package project.models;

import java.util.List;
import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class User implements Model<User> {
    private int id;
    private String name;
    private String secondName;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName);
    }

    @Override
    public String toString() {
        return "User:" + id +
                ", name: " + name  +
                ", secondName: " + secondName;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) throws Exception {
        return null;
    }

    @Override
    public User getAll(User user) {
        return null;
    }
}
