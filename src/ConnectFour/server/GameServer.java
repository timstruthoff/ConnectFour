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
        System.out.println("New connection from " + pClientIP + ":" + pClientPort);
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    @Override
    public void processMessage(String pClientIp, int pClientPort, String pMessage) {

        System.out.println("Client message from " + pClientIp + ":" + pClientPort + newline + pMessage);

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

        for (Player currentPlayer : allPlayers) {
            String currentIp = currentPlayer.getIpAddress();
            int currentPort = currentPlayer.getPort();

            // If player is not the one that has just joined
            System.out.println(currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());
            if (!(currentIp.equals(pClientIp) && currentPort == pClientPort)) {
                System.out.println("true " + currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());

                // Notify player that a new enemy has joined.
                String message = "NEWENEMY " + currentPlayer.getName() + " " + currentIp + " " + currentPort;
                this.send(currentIp, currentPort, message);
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

        if (servergamelogic.isValidColumnNumber(column)) {
            if (servergamelogic.isColumnFull(column)) {
                this.send(pClientIP, pClientPort, "ERR column full");
            } else {
                Player p = servergamelogic.getPlayerStore().getPlayerBySocket(pClientIP, pClientPort);

                // Check if player exists
                if (p != null) {

                    // Reflect drop in game server model and then notify game server of new drop.
                    servergamelogic.drop(p.getID(), column);
                } else {
                    this.send(pClientIP, pClientPort, "ERR No player found");
                }
            }
        } else {
            this.send(pClientIP, pClientPort, "ERR invalid column selected");
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
     * Handle a player's move in which they drop a chip in a specific column.
     *
     * @param pPlayerNumber The number of the player who dropped a chip.
     * @param pColumn
     * @param pRow
     */
    public void handlePlayerDrop(String pPlayerId, int pColumn, int pRow) {
        System.out.println("DROP Player: " + pPlayerId + " Column: " + pColumn + " Row: " + pRow);

        // Send message to other player
        this.sendToAll("DROPPED " + pPlayerId + " " + pColumn + " " + pRow);
    }

}
