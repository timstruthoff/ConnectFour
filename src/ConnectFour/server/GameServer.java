package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;
import EgJavaLib2.netzwerk.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameServer extends Server {

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
        System.out.println("New connection from " + pClientIP + ":" + pClientPort);
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    @Override
    public void processMessage(String pClientIp, int pClientPort, String pMessage) {

        System.out.println("// Client message from " + pClientIp + ":" + pClientPort + newline + "// " + pMessage);

        // Get the position where the string ends
        int commandEndIndex = pMessage.indexOf(" ");

        String command = "";
        String parameter = "";

        // Check if there are paramters in the message.
        if (commandEndIndex > -1) {
            // Get command from message
            command = pMessage.substring(0, commandEndIndex);

            // Get parameter from message after space
            parameter = pMessage.substring(commandEndIndex + 1);
        } else {
            command = pMessage;
        }

        switch (command) {
            case "START":
                this.onStartMessage(pClientIp, pClientPort);
                break;
            case "LOGIN":
                this.onLoginMessage(pClientIp, pClientPort, parameter);
                break;
            case "DROP":
                this.onDropMessage(pClientIp, pClientPort, parameter);
                break;
            default:
                this.send(pClientIp, pClientPort, "ERR Invalid command!");
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

        if (this.servergamelogic.isGameActive()) {

            this.send(pClientIP, pClientPort, "START");
        } else {
            this.send(pClientIP, pClientPort, "OK Waiting for players");

        }

    }

    /**
     * Process login messages from the client.
     *
     * @param pClientIp
     * @param pClientPort
     * @param pName
     */
    public void onLoginMessage(String pClientIp, int pClientPort, String pName) {

        System.out.println("Login call: " + pName + pClientIp + pClientPort);

        Player p = servergamelogic.addPlayer(pName, pClientIp, pClientPort);
        this.send(pClientIp, pClientPort, "OK ");

        // Notify the game logic that a new player has just joined.
        this.servergamelogic.onNewPlayer(pClientIp, pClientPort, pName);
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

        Player p = servergamelogic.getPlayerStore().getPlayerBySocket(pClientIP, pClientPort);

        if (p == null) {
            throw new IllegalArgumentException("Player not found!");
        }
        String ableToDrop = this.servergamelogic.ableToDrop(pClientIP, pClientPort, column);

        if (ableToDrop.equals("SUCCESS")) {

            int row = servergamelogic.drop(pClientIP, pClientPort, column);

            System.out.println("DROP Player: " + p.getName() + " Column: " + pColumn + " Row: " + row);

            // Send message to other player
            this.sendToAll("DROPPED " + p.getName() + " " + pColumn + " " + row);
        } else {
            this.send(pClientIP, pClientPort, "ERR " + ableToDrop);
        }
    }

    public void sendNewPlayer(String pRecipientIp, int pRecipientPort, String pNewPlayerName) {
        this.send(pRecipientIp, pRecipientPort, "NEWENEMY " + pNewPlayerName);
    }

    /**
     * Send a message notifying the players that the game has ended and whether
     * they have won or not.
     */
    public void sendGameEnded(Player pWinner) {

        // Notify the player that the game has ended and whether they won or not.
        this.sendToAll("END " + pWinner.getName());
    }

    /**
     * Notify all players, that the game can now start. Called when there are
     * enough players or when the game just ended and can start again now.
     */
    public void sendGameStart() {
        this.sendToAll("START");
    }

    /**
     * Send a message to all clients indicating, that it's another player's
     * turn.
     *
     * @param pName The socket of the player whose turn it is.
     */
    public void sendTurn(String pName) {
        this.sendToAll("TURN " + pName);
    }

}
