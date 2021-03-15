package multithreading.project;

import java.io.*;
import java.net.Socket;

public class ServerAccess extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerAccess(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run(){
        String word;
        while (true){
            try {
                word = in.readLine();
                if (word.equalsIgnoreCase("stop")) break;
                for (ServerAccess serverAccess : Server.list){
                    serverAccess.send(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private synchronized void send(String msg){
       try {
           out.write(msg+"\n");
           out.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
