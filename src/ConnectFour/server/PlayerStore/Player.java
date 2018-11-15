package ConnectFour.server.PlayerStore;

import java.util.UUID;

public class Player {

    private UUID id;
    private String name;
    private String ipAddress;
    private int port;

    public Player(String pName, String pIpAddress, int pPort) {
        id = UUID.randomUUID();
        name = pName;
        ipAddress = pIpAddress;
        port = pPort;
    }

    public String getID() {
        return id.toString();
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

}
