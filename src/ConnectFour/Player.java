package ConnectFour;

public class Player {
    private String name;

    public Player(String pName){
        name = pName;
    }

    public void setzeName(String pName) {
        name = pName;
    }

    public String gibName(){
        return name;
    }
    
}
