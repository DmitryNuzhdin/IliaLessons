package project.data;

public class JDBCDataStorageTest extends AbstractDataStorageTest {
    @Override
    public DataStorage createDataStorage() {
        return new JDBCDataStorage("jdbc:h2:/home/ilia/home/IliaLessons/db/testJUnit");
    }
}