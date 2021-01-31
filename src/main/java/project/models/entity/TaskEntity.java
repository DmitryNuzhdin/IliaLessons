package project.models.entity;

import project.models.TaskData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TASKS")
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private long taskId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    private UserEntity userEntity;

    @Column(name = "TITLE")
    private String title;
    @Column(name = "FULL_TASK_TEXT")
    private String fullTaskText;
    @Column(name = "iS_SOLVED")
    private boolean isSolved;

    public TaskEntity() {
    }

    public TaskEntity(UserEntity user, TaskData task) {
        this.userEntity = user;
        this.title = task.getTitle();
        this.fullTaskText = task.getFullTaskText();
        this.isSolved = task.isSolved();
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullTaskText() {
        return fullTaskText;
    }

    public void setFullTaskText(String fullTaskText) {
        this.fullTaskText = fullTaskText;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }


    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskId=" + taskId +
                ", userId=" + userEntity.getUserId() +
                ", title='" + title + '\'' +
                ", fullTaskText='" + fullTaskText + '\'' +
                ", isSolved=" + isSolved +
                '}';
    }
}
