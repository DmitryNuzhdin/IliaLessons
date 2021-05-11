package project.services.console;

import project.exceptions.UserNotFoundException;
import project.models.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Ilia Moskalenko
 */
public interface Command {
     void execute(BufferedReader bufferedReader, User user) throws IOException, UserNotFoundException;
     String getTitle();
}
