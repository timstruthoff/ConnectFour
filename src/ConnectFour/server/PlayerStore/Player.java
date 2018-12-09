package ConnectFour.server.PlayerStore;

import java.util.UUID;

/**
 * A player in the game.
 */
public class Player {

    private UUID id;
    private String name;
    private String ipAddress;
    private int port;

    /**
     * Create a new player.
     *
     * @param pName The name of the player.
     * @param pIpAddress The IP address of the player.
     * @param pPort The port of the player.
     */
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
