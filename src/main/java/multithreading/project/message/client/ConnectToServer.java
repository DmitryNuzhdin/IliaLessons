package multithreading.project.message.client;

import multithreading.project.message.ClientMessage;

public class ConnectToServer implements ClientMessage {
    public final String id;

    public ConnectToServer(String id) {
        this.id = id;
    }
}
