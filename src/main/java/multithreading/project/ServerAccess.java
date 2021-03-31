package multithreading.project;

import multithreading.project.message.ClientMessage;
import multithreading.project.message.Message;
import multithreading.project.message.client.ConnectToServer;
import multithreading.project.message.client.DisconnectFromServer;
import multithreading.project.message.client.UpdateAllParameters;
import multithreading.project.message.client.UpdateParameter;
import multithreading.project.message.server.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerAccess implements Runnable {
    private Socket socket;

    private String name;

    public String getName() {
        return name;
    }

    private Map<String, String> data = new ConcurrentHashMap<>();


    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    public ServerAccess(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());


            while (true) {
                Object o = objectInputStream.readObject();

                if (o instanceof ClientMessage) {
                    ClientMessage cm = (ClientMessage) o;

                    if (cm instanceof ConnectToServer) {
                        name = ((ConnectToServer) cm).id;
                        System.out.println("Client: " + name + " is connected.");
                        this.send(new ConnectionOpened());
                    } else if (cm instanceof UpdateParameter) {
                        UpdateParameter up = (UpdateParameter) cm;

                        Server.readWriteLock.writeLock().lock();
                        this.data.put(up.key, up.value);
                        Server.map.put(name, this);
                        Server.readWriteLock.writeLock().unlock();

                        Server.readWriteLock.writeLock().lock();
                        for (ServerAccess serverAccess : Server.list) {
                            serverAccess.send(new ClientParameter(name, up.key, up.value));
                        }
                        Server.readWriteLock.writeLock().unlock();
                    } else if (cm instanceof UpdateAllParameters) {
                        UpdateAllParameters uap = (UpdateAllParameters) cm;
                        this.data = uap.data;

                        Server.readWriteLock.writeLock().lock();
                        Server.map.put(name, this);
                        for (ServerAccess serverAccess : Server.list) {
                            serverAccess.send(new ClientParameters(name, uap.data));
                        }
                        Server.readWriteLock.writeLock().unlock();
                    } else if (cm instanceof DisconnectFromServer) {
                        this.send(new ClientDisconnected(name));
                        this.send(new ConnectionClosed("Client logout"));
                        this.socket.close();
                        objectOutputStream.close();
                        objectInputStream.close();
                    }

                }

                /*if (o instanceof String) {
                    String s = (String) o;

                    if (s.equalsIgnoreCase("stop")) {
                        this.send(new ConnectionClosed(""));
                        break;}

                    Server.readWriteLock.readLock().lock();

                    for (ServerAccess serverAccess : Server.list){
                        serverAccess.send(new ConnectionClosed());
                    }

                    Server.readWriteLock.readLock().unlock();

                }*/

            }
        } catch (Exception e) {
            this.send(new ConnectionClosed(e.getMessage()));
            e.printStackTrace();
        } finally {
            this.send(new ConnectionClosed("Connection closed"));
            try {
                this.socket.close();
                objectInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void send(Message msg) {
        try {
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
