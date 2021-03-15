package multithreading.project;

import multithreading.project.message.ClientMessage;
import multithreading.project.message.ConnectToServer;
import multithreading.project.message.UpdateParameter;

import java.io.*;

import java.net.Socket;

public class ServerAccess implements Runnable{
    private Socket socket;
    private String name;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;



    public ServerAccess(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());


            while (true){
                Object o = objectInputStream.readObject();

                if (o instanceof ClientMessage) {
                    ClientMessage cm = (ClientMessage) o;

                    if (cm instanceof ConnectToServer) {
                        String clientName = ((ConnectToServer) cm).id;

                        name = clientName;

                    } else if(cm instanceof UpdateParameter) {
                        UpdateParameter up = (UpdateParameter) cm;
                    }

                }



                if (o instanceof String) {
                    String s = (String) o;

                    if (s.equalsIgnoreCase("stop")) break;

                    Server.readWriteLock.readLock().lock();

                    for (ServerAccess serverAccess : Server.list){
                        serverAccess.send(s);
                    }

                    Server.readWriteLock.readLock().unlock();

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void send(String msg){
       try {
           objectOutputStream.writeObject(msg);
           objectOutputStream.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
