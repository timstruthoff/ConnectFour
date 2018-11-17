package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;
import EgJavaLib2.netzwerk.*;
import java.util.List;

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

        this.send(pClientIP, pClientPort, "OK Game start");

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

        List<Player> allPlayers = servergamelogic.getPlayerStore().getAllPlayers();

        System.out.println("Searching for other players:");

        // Sending a message to all other players indicating that a new player has joined.
        for (Player currentPlayer : allPlayers) {
            String currentIp = currentPlayer.getIpAddress();
            int currentPort = currentPlayer.getPort();

            // Notify newly joined player that this player was already in the game.
            this.send(pClientIp, pClientPort, "NEWENEMY " + currentPlayer.getName());

            // If player is not the one that has just joined
            if (currentIp.equals(pClientIp) && currentPort == pClientPort) {
                System.out.println("Joined: " + currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());
            } else {
                System.out.println("Other: " + currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());

                // Notify player that a new enemy has joined.
                this.send(currentIp, currentPort, "NEWENEMY " + p.getName());

            }
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

    /**
     * Send a message notifying the players that the game has ended and whether
     * they have won or not.
     */
    public void sendGameEnded() {

        // Notify the player that the game has ended and whether they won or not.
        Player winner = servergamelogic.getActivePlayer();
        this.send(winner.getIpAddress(), winner.getPort(), "END true");

        List<Player> loosers = servergamelogic.getInactivePlayers();
        for (Player looser : loosers) {
            this.send(looser.getIpAddress(), looser.getPort(), "END false");
        }
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
