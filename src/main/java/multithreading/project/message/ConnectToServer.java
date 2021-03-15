package multithreading.project.message;

import java.io.Serializable;

public class ConnectToServer implements ClientMessage {
    public final String id;

    public ConnectToServer(String id) {
        this.id = id;
    }
}
