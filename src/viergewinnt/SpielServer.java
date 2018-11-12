package viergewinnt;

import EgJavaLib2.netzwerk.*;
/**
 * Write a description of class SpielServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpielServer extends Server{

    VierGewinntSpiel dasSpiel;

    /**
     * Constructor for objects of class SpielServer
     */
    public SpielServer(){
        super(1234);
        dasSpiel = new VierGewinntSpiel();
        dasSpiel.setzeServer(this);
    }

    public void processNewConnection(String pClientIP, int pClientPort){

    }

    public void processClosingConnection(String pClientIP, int pClientPort){

    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage){       

    }

}
