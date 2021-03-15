package multithreading.project;

import java.io.IOException;

public class Client {
    public static String IP_ADDRESS = "localhost";
    public static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        new ClientAccess(IP_ADDRESS, PORT);
    }
}
