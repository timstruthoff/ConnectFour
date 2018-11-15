package ConnectFour.server;

public class Player {

    private String name;
    private String ipAddress;
    private int port;

    public Player(String pName) {
        name = pName;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public void setIpAddress(String pIpAddress) {
        ipAddress = pIpAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setPort(int pPort) {
        port = pPort;
    }

    public int getPort() {
        return port;
    }

}
