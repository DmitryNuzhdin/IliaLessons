package multithreading.project;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientAccess {
    private Socket socket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;

    private Map<String, String> data;

    private String address;
    private int port;

    private String nickName;
    private String sex;
    private String position;
    private String salary;


    public ClientAccess(String ipAddress, int port) throws IOException {
        this.address = ipAddress;
        this.port = port;
        try {
            this.socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            data = updateData();

            new SendUpdate().start();
            new AcceptUpdate().start();
        } catch (IOException e) {

        }
    }

    private void updateParameter(String key, String value) {
        if (data.containsKey(key)) data.put(key, value);
    }

    private String getKey() throws IOException {
        String key;
        System.out.println("Enter kye: ");
        key = reader.readLine();
        return key;
    }

    private String getValue() throws IOException {
        String value;
        System.out.println("Enter value: ");
        value = reader.readLine();
        return value;
    }

    private void updateAllParameters(Map<String, String> data) {
        this.data = data;
    }

    private Map<String, String> updateData() {
        Map<String, String> map = new HashMap<>();
        this.pressNick();
        this.pressSex();
        this.pressPosition();
        this.pressSalary();
        map.put("nickName", nickName);
        map.put("sex", sex);
        map.put("position", position);
        map.put("salary", salary);
        return map;
    }


    private void pressNick() {
        System.out.println("Press your nick: ");
        try {
            nickName = reader.readLine();
            out.write("Hello " + nickName + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pressSex() {
        System.out.println("Press your sex: ");
        try {
            sex = reader.readLine();
            out.write("Your sex: " + sex + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pressPosition() {
        System.out.println("Press your position: ");
        try {
            position = reader.readLine();
            out.write("Your position: " + position + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pressSalary() {
        System.out.println("Press your salary: ");
        try {
            salary = reader.readLine();
            out.write("Your salary: " + salary + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }


    private class SendUpdate extends Thread {
        private boolean isOpen;

        @Override
        public void run() {
            while (isOpen) {
                String word;
                try {
                    word = reader.readLine();
                    switch (word.toLowerCase().trim()) {
                        case "stop":
                            isOpen = false;
                            out.write("Stop!\n");
                            ClientAccess.this.downService();
                            break;
                        case "update":
                            updateParameter(getKey(), getValue());
                            out.write(data);
                            break;
                        case "all":
                            updateAllParameters(updateData());
                            out.write(data);
                            break;
                        default:
                            System.out.println("Unknown command");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class AcceptUpdate extends Thread {

        @Override
        public void run() {
            while (true) {
                String str;
                try {
                    str = in.readLine();
                    if (str.equalsIgnoreCase("stop")) {
                        ClientAccess.this.downService();
                        break;
                    } else {
                        out.write(data);
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientAccess.this.downService();
                }
            }
        }

    }

}
