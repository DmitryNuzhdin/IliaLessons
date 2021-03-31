package multithreading.project;

import multithreading.project.message.Message;
import multithreading.project.message.client.ConnectToServer;
import multithreading.project.message.client.DisconnectFromServer;
import multithreading.project.message.client.UpdateAllParameters;
import multithreading.project.message.client.UpdateParameter;
import multithreading.project.message.server.*;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
                    if (o instanceof ConnectionOpened) {
                        ConnectionOpened connectionOpened = (ConnectionOpened) o;
                        System.out.println("Connection opened");
                    } else if (o instanceof ClientParameter) {
                        ClientParameter clientParameter = (ClientParameter) o;
                        System.out.println("Client: " + clientParameter.name +
                                " refresh parameter: " +
                                clientParameter.key + ": " + clientParameter.value);
                    } else if (o instanceof ClientParameters) {
                        ClientParameters clientParameters = (ClientParameters) o;
                        System.out.println("Client: " + clientParameters.name +
                                " refresh all parameters: ");
                        for (Map.Entry<String, String> param : clientParameters.data.entrySet()) {
                            System.out.println(param.getKey() + ": " + param.getValue());
                        }
                    } else if (o instanceof ClientDisconnected) {
                        ClientDisconnected clientDisconnected = (ClientDisconnected) o;
                        System.out.println("Client: " + clientDisconnected.name + " disconnected");
                        closeAll(socket, objectOutputStream, objectInputStream);
                    } else if (o instanceof ConnectionClosed) {
                        ConnectionClosed connectionClosed = (ConnectionClosed) o;
                        System.out.println(connectionClosed.reason);
                        closeAll(socket, objectOutputStream, objectInputStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    closeAll(socket, objectOutputStream, objectInputStream);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String name;
            System.out.println("Enter your name");
            name = br.readLine();
            write(new ConnectToServer(name), objectOutputStream);

            System.out.println("Введите stop - чтобы отключиться;\n" +
                    "up - чтобы обновить 1 параметр\n" +
                    "uap - чтобы обновить все параметры");

            while (true) {
                String read = br.readLine();
                if (read.equalsIgnoreCase("stop")) {
                    write(new DisconnectFromServer(), objectOutputStream);
                    break;
                } else if (read.equalsIgnoreCase("up")) {
                    write(new UpdateParameter("param1", "value1"), objectOutputStream);
                } else if (read.equalsIgnoreCase("uap")) {
                    Map<String, String> data = new ConcurrentHashMap<>();
                    data.put("k1","v1");
                    write(new UpdateAllParameters(data), objectOutputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeAll(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
        oos.close();
        ois.close();
        socket.close();
    }

    private static void write(Message message, ObjectOutputStream oos) {
        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
