package multithreading.project;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private static final int PORT = 8080;
    public static List<ServerAccess> list = new ArrayList<>();
    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static ExecutorService executorService = Executors.newFixedThreadPool(50);
    public static Map<String, ServerAccess> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                Socket socket = server.accept();
                try {
                    readWriteLock.writeLock().lock();

                    ServerAccess sa = new ServerAccess(socket);
                    executorService.execute(sa);
                    list.add(sa);

                    readWriteLock.writeLock().unlock();
                } catch (IOException e) {
                    socket.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

