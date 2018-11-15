package ConnectFour.client;

import EgJavaLib2.netzwerk.*;
import javax.swing.JOptionPane;

/**
 * Write a description of class NetworkingClient here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NetworkingClient extends Client {

    private GameWindow window;
    private ConnectFourGame gameLogic;

    /**
     * Constructor for objects of class SpielClient
     *
     * @param pServerIP
     */
    public NetworkingClient(String pServerIP) {
        super(pServerIP, 1234);
        this.initialSetup();
    }

    public void initialSetup() {
        this.send("START");
    }

    /**
     *
     * @param pMessage
     */
    @Override
    public void processMessage(String pMessage) {

        // Get the position where the string ends
        int commandEndIndex = pMessage.indexOf(" ");

        // Get command from message
        String command = pMessage.substring(0, commandEndIndex);

        // Get parameter from message after space
        String parameter = pMessage.substring(commandEndIndex + 1);

        switch (command) {
            case "NEWENEMY":
                String enemyName = parameter;
                break;
            default:
                break;
        }
    }

    public void setWindow(GameWindow pWindow) {
        window = pWindow;
    }

    public void sendPlayerName(String pName) {
        this.send("LOGIN " + pName);
    }

    public void sendDrop(int pColumn) {
        this.send("DROP " + pColumn);
    }

}
