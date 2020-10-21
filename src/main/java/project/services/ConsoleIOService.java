package project.services;

import project.models.Model;

public class ConsoleIOService implements IOService {
    private Model model;

    public ConsoleIOService(Model model) {
        this.model = model;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
