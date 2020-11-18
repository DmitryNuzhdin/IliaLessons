package project.data;

import org.junit.Before;
import org.junit.Test;
import project.models.User;
import project.models.UserData;

import static org.junit.Assert.assertEquals;

public abstract class AbstractDataStorageTest {
    public DataStorage storage;

    public abstract DataStorage createDataStorage();

    @Before
    public void before(){
        storage = createDataStorage();
    }

    @Test
    public void addUser() {
        User user = storage.addUser(new UserData("a", "b"));
        assertEquals(new User(1,"a", "b"), user);
    }

    @Test
    public void getUser() {
        User user = storage.addUser(new UserData("a", "b"));
        assertEquals(new User(1,"a", "b"), storage.getUser(user.getId()).get());
    }

    @Test
    public void updateTask() {

    }
}