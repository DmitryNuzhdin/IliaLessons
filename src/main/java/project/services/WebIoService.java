package project.services;

/**
 * @author Ilia Moskalenko
 */
public class WebIoService implements IOService{
    @Override
    public void connect(String host, int port) throws Exception {

    }

    @Override
    public void reconnect() throws Exception {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public String getHostName() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
