package ConnectFour;

public class Player {
    private String name;
    private String ipAddress;

    public Player(String pName){
        name = pName;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getName(){
        return name;
    }
    
    public void setIpAddress(String pIpAddress) {
        ipAddress = pIpAddress;
    }

    public String getIpAddress(){
        return ipAddress;
    }
    
}
