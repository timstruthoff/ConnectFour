package ConnectFour;

import EgJavaLib2.netzwerk.*;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameServer extends Server{

    ConnectFourGame dasSpiel;

    /**
     * Constructor for objects of class SpielServer
     */
    public GameServer(){
        super(1234);
    }

    public void processNewConnection(String pClientIP, int pClientPort){

    }

    public void processClosingConnection(String pClientIP, int pClientPort){

    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage){       

    }

}
