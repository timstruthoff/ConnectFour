package ConnectFour;

import EgJavaLib2.netzwerk.*;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameServer extends Server {

    ServerGameLogic servergamelogic = new ServerGameLogic();

    /**
     * Constructor for objects of class SpielServer
     */
    public GameServer() {
        super(1234);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {

    }

    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        // Get the position where the string ends
        int commandEndIndex = pMessage.indexOf(" ");

        // Get command from message
        String command = pMessage.substring(0, commandEndIndex);

        // Get parameter from message after space
        String parameter = pMessage.substring(commandEndIndex + 1);

        switch (command) {
            case "START":
                this.onStartMessage(pClientIP, pClientPort);
                break;
            case "LOGIN":
                this.onLoginMessage(pClientIP, pClientPort, parameter);
                break;
            case "DROP":
                this.onDropMessage(pClientIP, pClientPort, parameter);
                break;
            default:
                this.send(pClientIP, pClientPort, "ERR Invalid command!");
                break;
        }
    }
    
    /**
     * Process start messages from the client.
     * @param pClientIP
     * @param pClientPort 
     */
    public void onStartMessage(String pClientIP, int pClientPort) {
        int playerNumber = servergamelogic.getNumberOfPlayers();
        
        // Check how many players are already on the server.
        if (playerNumber > 2) {
            this.send(pClientIP, pClientPort, "ERR Game full");
        } else if (playerNumber == 0) {
            this.send(pClientIP, pClientPort, "OK Waiting for players");
        } else if (playerNumber == 1) {
            this.send(pClientIP, pClientPort, "OK Game start");
        }
    }
    
    /**
     * Process login messages from the client.
     * @param pClientIP
     * @param pClientPort
     * @param pName 
     */
    public void onLoginMessage(String pClientIP, int pClientPort, String pName) {
        if (servergamelogic.isNameAlreadyTaken(pName)) {
            this.send(pClientIP, pClientPort, "ERR Name already taken");
        } else {
            servergamelogic.addPlayer(pName, pClientIP);
            this.send(pClientIP, pClientPort, "OK Login was successful");
        }
        
    }
    
    /**
     * Drops a chip in the column.
     * @param pClientIP 
     * @param pClientPort
     * @param pColumn The column where the chip should be dropped.
     */
    public void onDropMessage(String pClientIP, int pClientPort, String pColumn) {
        int column = Integer.parseInt(pColumn);
        
        if (servergamelogic.isValidColumnNumber(column)) {
            if (servergamelogic.isColumnFull(column)) {
                this.send(pClientIP, pClientPort, "ERR column full");
            } else {
                Player p = servergamelogic.getPlayerByIpAddress(pClientIP);
                int playerNumber = servergamelogic.getNumberOfPlayer(p) ;
                servergamelogic.drop(playerNumber, column);
            }
        } else {
            this.send(pClientIP, pClientPort, "ERR invalid column selected");        }
    }

}
