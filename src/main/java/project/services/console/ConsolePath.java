package project.services.console;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConsolePath {
    final ConsolePath previous;
    final String message;
    final Predicate<String> matcher;
    final ConsoleCommandAction action;

    public ConsolePath(ConsolePath previous, String message, Predicate<String> matcher, ConsoleCommandAction action) {
        this.previous = previous;
        this.message = message;
        this.matcher = matcher;
        this.action = action;
    }
}

