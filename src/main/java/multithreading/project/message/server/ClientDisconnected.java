package multithreading.project.message.server;

import multithreading.project.message.ServerMessage;

public class ClientDisconnected implements ServerMessage {
    public final String name;

    public ClientDisconnected(String name) {
        this.name = name;
    }
}
