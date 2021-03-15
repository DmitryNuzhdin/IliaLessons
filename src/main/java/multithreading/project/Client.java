package multithreading.project;

import java.io.*;
import java.net.Socket;
import java.util.SortedMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static String IP_ADDRESS = "localhost";
    public static int PORT = 8080;
    public static ExecutorService es = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(IP_ADDRESS, PORT);

        //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        es.execute(() -> {
            try {
                while (true) {
                    Object o = objectInputStream.readObject();
                    if (o instanceof String) {
                        String s = (String) o;
                        System.out.println(s);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String read = br.readLine();
                objectOutputStream.writeObject(read);
                objectOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
