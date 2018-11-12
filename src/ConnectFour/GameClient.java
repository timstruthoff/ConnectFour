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
    private ConnectFourGame gameLogic;

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
        
        // Get the position where the string ends
        int commandEndIndex = pMessage.indexOf(" ");
        
        // Get command from message
        String command = pMessage.substring(0, commandEndIndex);
   
        // Get parameter from message after space
        String parameter = pMessage.substring(commandEndIndex + 1);
        
        switch (command) {
            case "NEWENEMY":
                String enemyName = parameter;
                gameLogic.onNewEnemy(enemyName);
                break;
            default:
                break;
        } 
    }

    public void setWindow(GameWindow pWindow){
        window = pWindow;
    }

}
