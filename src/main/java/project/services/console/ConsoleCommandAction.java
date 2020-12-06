package project.services.console;

import project.models.Model;
import project.models.User;

import java.util.Deque;

public interface ConsoleCommandAction {
    void action(Model model, User user, Deque<String> lastCommands);
}
