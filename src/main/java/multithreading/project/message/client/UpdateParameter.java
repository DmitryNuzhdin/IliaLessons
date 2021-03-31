package multithreading.project.message.client;

import multithreading.project.message.ClientMessage;

public class UpdateParameter implements ClientMessage {
    public final String key;
    public final String value;

    public UpdateParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
