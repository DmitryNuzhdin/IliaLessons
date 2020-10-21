package project.services;

/**
 * @author Ilia Moskalenko
 */
public interface IOService {
     void connect(String host, int port) throws Exception;
     void reconnect() throws Exception;
     void disconnect();
     String getHostName();
     int getPort();
     boolean isConnected();

}
