package project.models;

import java.util.List;
import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class User {
    private static long countUserId;
    private final long id;
    private final String name;
    private final String secondName;


    public User(String name, String secondName) {
        this.id = countUserId;
        this.name = name;
        this.secondName = secondName;
        countUserId++;
    }

    public long getId() {
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


}
