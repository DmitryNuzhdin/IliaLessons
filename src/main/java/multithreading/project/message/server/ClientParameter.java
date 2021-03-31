package multithreading.project.message.server;

import multithreading.project.message.ServerMessage;

public class ClientParameter implements ServerMessage {
    public final String name;
    public final String key;
    public final String value;

    public ClientParameter(String name, String key, String value) {
        this.name = name;
        this.key = key;
        this.value = value;
    }
}
