package viergewinnt;

public class Spieler {
    private String name;

    public Spieler(String pName){
        name = pName;
    }

    public void setzeName(String pName) {
        name = pName;
    }

    public String gibName(){
        return name;
    }
    
}
