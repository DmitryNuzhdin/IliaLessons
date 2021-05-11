package project.data;

import project.models.entity.TaskRepo;
import project.models.entity.UserRepo;

public class JpaDataStorageTest extends AbstractDataStorageTest{

    private UserRepo userRepo;
    private TaskRepo taskRepo;

    @Override
    public DataStorage createDataStorage() {
        return new JpaDataStorage(userRepo, taskRepo);
    }
}