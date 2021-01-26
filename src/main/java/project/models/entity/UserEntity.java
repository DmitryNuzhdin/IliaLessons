package project.models.entity;

import project.models.UserData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "NAME")
    private String name;
    @Column(name = "SECOND_NAME")
    private String secondName;

    public UserEntity() {
    }

    public UserEntity(UserData user) {
        this.name = user.getName();
        this.secondName = user.getSecondName();
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity")
    private List<TaskEntity> taskList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<TaskEntity> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
