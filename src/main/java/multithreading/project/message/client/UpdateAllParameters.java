package multithreading.project.message.client;

import multithreading.project.message.ClientMessage;

import java.util.Map;

public class UpdateAllParameters implements ClientMessage {
    public final Map<String, String> data;

    public UpdateAllParameters(Map<String, String> data) {
        this.data = data;
    }
}
