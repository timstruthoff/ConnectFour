package viergewinnt;

import EgJavaLib2.netzwerk.*;
/**
 * Write a description of class SpielClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpielClient extends Client{
    private SpielFenster fenster;

    /**
     * Constructor for objects of class SpielClient
     */
    public SpielClient(String pServerIP){
        super(pServerIP,1234);
    }

    public void processMessage(String pMessage){

    }

    public void setzeFenster(SpielFenster pFenster){
        fenster = pFenster;
    }

}
