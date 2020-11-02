package project.models.twoUsers;

import java.util.Objects;

public class User extends UserData{
    private final long id;

    public User(long id, String firstName, String secondName) {
        super(firstName, secondName);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
