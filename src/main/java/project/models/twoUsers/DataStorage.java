package project.models.twoUsers;

public interface DataStorage {
    User addUser(UserData userData);
    User updateUser(long id, UserData userData);
    User getUser(long id);
}
