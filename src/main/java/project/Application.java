package project;

import project.data.DataStorage;
import project.data.InMemoryDataStorage;
import project.data.JDBCDataStorage;
import project.models.Model;
import project.models.ModelImpl;
import project.services.IOService;
import project.services.ConsoleIOService;
import project.services.JDBCService;

public class Application {
    public static void main(String[] args) {
        DataStorage dataStorage = new JDBCDataStorage();
        Model model = new ModelImpl(dataStorage);
        IOService ioService = new ConsoleIOService(model);
     //   IOService ioService2 = new ConsoleIOService(model);

        ioService.start();
       // ioService2.start();

        //contex.getAllClasses(ConsoleIOService.class).start();

    }
}
