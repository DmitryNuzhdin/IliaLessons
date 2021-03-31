package multithreading.project.message.server;

import multithreading.project.message.ServerMessage;

public class ConnectionClosed implements ServerMessage {
    public final String reason;

    public ConnectionClosed(String reason) {
        this.reason = reason;
    }
}
