package multithreading.project;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8080;
    public static List<ServerAccess> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                try {
                    list.add(new ServerAccess(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

