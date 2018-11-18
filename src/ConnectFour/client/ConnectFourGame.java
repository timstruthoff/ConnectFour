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

    private boolean gameActive = false;

    private List<String> playerNames = new ArrayList<String>();
    private String[] playerColors = {"blue", "red", "green"};
    private String myName;

    public ConnectFourGame(NetworkingClient pClient, GameWindow pWindow) {
        client = pClient;
        window = pWindow;

        // Ask for player name and then send it to the server.
        myName = window.askForPlayerName();
        client.sendPlayerName(myName);
    }

    public ConnectFourGame(NetworkingClient pClient, GameWindow pWindow, String pPlayerName) {
        client = pClient;
        window = pWindow;
        myName = pPlayerName;
        client.sendPlayerName(myName);

    }

    /**
     * Gets the color of a player.
     *
     * @param pPlayerName A player name.
     * @return The player's color.
     */
    public String getPlayerColor(String pPlayerName) {
        int playerIndex = playerNames.indexOf(pPlayerName);

        if (playerIndex < playerColors.length) {
            return playerColors[playerIndex];
        }
        return null;
    }

    public String getMyName() {
        return myName;
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
        String color = this.getPlayerColor(pPlayerName);

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

    /**
     * Called by the networking class when it receives a message from the
     * server, that the game has ended.
     *
     * @param pWinner The name of the winner of the current match.
     */
    public void onGameEnd(String pWinner) {
        if (pWinner.equals(myName)) {
            this.window.setResultGraphicText("You won!");
        } else {
            this.window.setResultGraphicText("You lost! Player " + pWinner + " won.");
        }
        this.setGameActive(false);
    }

    /**
     * Called by the networking class when the server sends a message to start
     * the game.
     */
    public void resetGame() {
        this.window.resetPlayingField();
    }

    /**
     * Called by the networking class when the server sends a message to start
     * the game.
     */
    public void onStart() {
        this.resetGame();
        this.setGameActive(true);
    }

    public boolean getGameActive() {
        return gameActive;
    }

    public void setGameActive(boolean pGameActive) {
        gameActive = pGameActive;
        if (gameActive) {
            this.window.activateGame();
        } else {
            this.window.deactivateGame();
        }
    }

}
