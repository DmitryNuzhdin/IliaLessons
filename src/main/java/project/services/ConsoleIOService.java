package project.services;

import project.models.Model;

public class ConsoleIOService implements IOService {
    private Model model;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            while (true){
//
//            }
        }
    };

    public ConsoleIOService(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        new Thread(runnable).start();
    }

    @Override
    public void stop() {

    }
}
