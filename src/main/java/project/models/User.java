package project.models;

import java.util.List;
import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class User {
    private long id;
    private String name;
    private String secondName;

    public User(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public User(long id, String name, String secondName) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public User withName(String name) {
        return new User(id, name, secondName);
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


}
