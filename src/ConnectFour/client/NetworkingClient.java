package ConnectFour.client;

import EgJavaLib2.netzwerk.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Create a networking client used to communicate with the game server.
 */
public class NetworkingClient extends Client {

    private int id = (int) (Math.random() * 899 + 100); // Random number between 100 and 999
    private GameWindow window;
    private ClientGameLogic gameLogic;

    String newline = System.getProperty("line.separator");

    /**
     * Create a networking client.
     *
     * @param pServerIP The IP address of a server.
     */
    public NetworkingClient(String pServerIP) {
        super(pServerIP, 1234);
        this.initialSetup();
    }

    public void setGameLogic(ClientGameLogic pGameLogic) {
        gameLogic = pGameLogic;
    }

    /**
     * Notify the server that the game client is ready.s
     */
    public void initialSetup() {
        this.send("START");
    }

    /**
     * Handle all messages coming from the server.
     *
     * @param pMessage
     */
    @Override
    public void processMessage(String pMessage) {

        System.out.println("\\\\ Server to client " + id + ":" + newline + "\\\\ " + pMessage);

        // Check if message is an error message
        if (pMessage.startsWith("ERR")) {
            this.onError(pMessage.replace("ERR ", ""));
        } else {

            List<String> messageParts = Arrays.asList(pMessage.split(" "));
            String command = messageParts.get(0);
            List<String> parameters = messageParts.subList(1, messageParts.size());

            switch (command) {
                case "OK":
                    break;
                case "START":
                    this.onStart();
                    break;
                case "NEWENEMY":
                    this.onNewPlayer(parameters.get(0));
                    break;
                case "TURN":
                    this.onTurn(parameters.get(0));
                    break;
                case "DROPPED":
                    this.onDropped(parameters);
                    break;
                case "END":
                    this.onEnd(parameters.get(0));
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Hanlde new player messages from the server.
     *
     * @param pName The name of the new player.
     */
    public void onNewPlayer(String pName) {
        gameLogic.addPlayer(pName);
    }

    public void onStart() {
        gameLogic.onStart();
    }

    public void onTurn(String pPlayerName) {
        gameLogic.changeTurn(pPlayerName);
    }

    /**
     * Handle dropped messages from the server. These message should include
     * three parameters: 1. The name of the player who dropped the chip 2. The
     * column number in which the chip was dropped. 3. The row number in which
     * the chip was dropped.
     *
     * @param p An array with the parameters as strings.
     */
    public void onDropped(List<String> p) {
        if (p.size() != 3) {
            throw new IllegalArgumentException("Invalid number of arguments in dropped command from server.");
        }

        // Parse the player name
        String playerName = p.get(0);

        // Parse and validate the column number
        int column = Integer.parseInt(p.get(1));
        if (column < 0 && column >= window.getNumberOfColumns()) {
            throw new IllegalArgumentException("Invalid column number " + column + ".");
        }

        // Parse and validate the row number
        int row = Integer.parseInt(p.get(2));
        if (row < 0 && row >= window.getNumberOfRows()) {
            throw new IllegalArgumentException("Invalid row number " + row + ".");
        }

        // Set the mark at the specified location.
        this.gameLogic.setMark(playerName, column, row);

        System.out.println(Arrays.toString(p.toArray()));
        System.out.println("Name: " + playerName + " column: " + column + " row: " + row);
    }

    /**
     * Handle error messages sent by the server.
     *
     * @param pErrorMessage The error message
     */
    public void onError(String pErrorMessage) {

        if (pErrorMessage.equals("Name already taken!")) {
            this.gameLogic.onInvalidPlayerName(pErrorMessage);
            System.out.println("Error: " + pErrorMessage);
        }
    }

    public void onEnd(String pWinner) {
        this.gameLogic.onGameEnd(pWinner);
    }

    public void setWindow(GameWindow pWindow) {
        window = pWindow;
    }

    /**
     * Send a message to the server indicating, that the user chose a new player
     * name.
     *
     * @param pName The new name.
     */
    public void sendPlayerName(String pName) {
        this.send("LOGIN " + pName);
    }

    /**
     * Send a request to the server for dropping a chip in the specified column.
     *
     * @param pColumn The column in which the chip should be dropped.
     */
    public void sendDrop(int pColumn) {
        this.send("DROP " + pColumn);
    }

}
