package ConnectFour;

import EgJavaLib2.netzwerk.*;

/**
 * Write a description of class GameClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameClient extends Client{
    private GameWindow window;

    /**
     * Constructor for objects of class SpielClient
     * @param pServerIP 
     */
    public GameClient(String pServerIP){
        super(pServerIP,1234);
    }

    /**
     *
     * @param pMessage
     */
    @Override
    public void processMessage(String pMessage){

    }

    public void setWindow(GameWindow pWindow){
        window = pWindow;
    }

}
