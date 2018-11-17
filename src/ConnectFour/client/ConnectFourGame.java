package ConnectFour.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConnectFourGame {

    private NetworkingClient client;
    private GameWindow window;

    private List<String> playerNames = new ArrayList<String>();
    private String[] playerColors = {"blue", "red", "green"};
    private String myName;

    public ConnectFourGame(NetworkingClient pClient, GameWindow pWindow) {
        client = pClient;
        window = pWindow;

        this.startGame();

    }

    public ConnectFourGame(NetworkingClient pClient, GameWindow pWindow, String pPlayerName) {
        client = pClient;
        window = pWindow;
        myName = pPlayerName;
        client.sendPlayerName(myName);

    }

    public void startGame() {

        // Ask for player name and then send it to the server.
        myName = window.askForPlayerName();
        client.sendPlayerName(myName);
    }

    public void addPlayer(String pName) {
        playerNames.add(pName);
        window.addPlayer(pName);
    }

    /**
     * Sets a mark on a specific position on the playing field. This mark is
     * stored in the string array with a specific character for each player.
     *
     * @param pColumn The column where the mark should be set.
     * @param pRow The row where the mark should be set.
     */
    public void setMark(String pPlayerName, int pColumn, int pRow) {
        int playerNumber = playerNames.indexOf(pPlayerName);

        // Checking if the player was found in the array.
        if (playerNumber < 0) {
            throw new IllegalArgumentException("Player name not found!");
        }

        // Getting the corresponding color.
        String color = playerColors[playerNumber];

        this.window.setFieldCellColor(pColumn, pRow, color);
    }

    /**
     * Drop a chip in a column. Calculates the position of the top spot and
     * places a chip there.
     *
     * @param pColumn
     * @param pColor
     * @return The row position of the chip.
     */
    public void drop(int pColumn) {
        client.sendDrop(pColumn);
    }

    /**
     * Pass the network client to this class.
     *
     * @param pClient The network game client.
     */
    public void setClient(NetworkingClient pClient) {
        client = pClient;
    }

    /**
     * Pass the window to this class.
     *
     * @param pClient The window object.
     */
    public void setWindow(GameWindow pWindow) {
        window = pWindow;
    }

}
