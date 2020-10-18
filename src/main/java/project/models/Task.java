package project.models;

import java.util.Objects;

/**
 * @author Ilia Moskalenko
 */
public class Task{
    private int id;
    private String title;
    private String fullTask;
    private boolean solved;



    public Task(int id, String title, String fullTask) {
        this.id = id;
        this.title = title;
        this.fullTask = fullTask;
        solved = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFullTask() {
        return fullTask;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id &&
                solved == task.solved &&
                Objects.equals(title, task.title) &&
                Objects.equals(fullTask, task.fullTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fullTask, solved);
    }

    @Override
    public String toString() {
        return "id:" + id +
                ", title: " + title +
                ", solved=" + solved;
    }


}
