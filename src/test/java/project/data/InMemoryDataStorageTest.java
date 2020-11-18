package project.data;

import org.junit.Before;
import org.junit.Test;
import project.models.*;

import static org.junit.Assert.*;

public class InMemoryDataStorageTest extends AbstractDataStorageTest {
    @Override
    public DataStorage createDataStorage() {
        return new InMemoryDataStorage();
    }
}