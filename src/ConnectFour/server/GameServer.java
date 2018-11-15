package ConnectFour.server;

import EgJavaLib2.netzwerk.*;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameServer extends Server {
    
    // NOTE: circular reference not used yet.
    ServerGameLogic servergamelogic = new ServerGameLogic(this);
    
    String newline = System.getProperty("line.separator");

    /**
     * Constructor for objects of class SpielServer
     */
    public GameServer() {
        super(1234);
        
        System.out.println("Server: Started.");
    }

    public void processNewConnection(String pClientIP, int pClientPort) {

    }

    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        
        System.out.println("Client message from " + pClientIP + ":" + pClientPort + newline + pMessage);
        
        
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
     *
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
     *
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
            
            // Notifying all players that a new enemy has joined.
            this.sendToAll("NEWENEMY " + pName);
        }

    }

    /**
     * Drops a chip in the column.
     *
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
                int playerNumber = servergamelogic.getNumberOfPlayer(p);
                
                // Reflect drop in game server model and then notify game server of new drop.
                servergamelogic.drop(playerNumber, column);
                
                
                if(servergamelogic.hasGameEnded()) {
                    this.sendGameEnded();
                } else {
                    
                    servergamelogic.switchPlayers();
                }
            }
        } else {
            this.send(pClientIP, pClientPort, "ERR invalid column selected");
        }
    }
    
    /**
     * Send a message notifying the players that the game has ended and whether they have won or not.
     */
    public void sendGameEnded() {
        
        // Notify the player that the game has ended and whether they won or not.
        Player winner = servergamelogic.getCurrentPlayer();
        this.send(winner.getIpAddress(), winner.getPort(), "END true");
        
        Player looser = servergamelogic.getOtherPlayer();
        this.send(looser.getIpAddress(), looser.getPort(), "END false");
    }
    
    /**
     * Handle a player's move in which they drop a chip in a specific column.
     * @param pPlayerNumber The number of the player who dropped a chip.
     * @param pColumn
     * @param pRow 
     */
    public void handlePlayerDrop (int pPlayerNumber, int pColumn, int pRow) {
        System.out.println("DROP Player: " + pPlayerNumber + " Column: " + pColumn + " Row: " + pRow);
        
        // Send message to other player
        this.sendToAll("DROPPED " + pPlayerNumber + " " + pColumn + " " + pRow);
    }

}
