package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class Task extends TaskData{
    private final long id;


    public Task(long id, String title, String fullTaskText, boolean solved) {
        super(title, fullTaskText, solved);
        this.id = id;
    }

    public Task updateId(long id){
        return new Task(id, getTitle(), getFullTaskText(), isSolved());
    }


    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "id: " + id +" " +
                super.toString();
    }

}
