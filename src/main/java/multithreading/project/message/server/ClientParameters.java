package multithreading.project.message.server;

import multithreading.project.message.ServerMessage;

import java.util.Map;

public class ClientParameters implements ServerMessage {
    public final String name;
    public final Map<String, String> data;

    public ClientParameters(String name, Map<String, String> data) {
        this.name = name;
        this.data = data;
    }


}
