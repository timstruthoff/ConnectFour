package ConnectFour.client;

import java.util.ArrayList;
import java.util.List;

/**
 * A model of the connect four game. This class includes the main logic of the
 * game.
 */
public class ClientGameLogic {

    private NetworkingClient networkingClient;
    private GameWindow gameWindow;

    private boolean gameActive = false;

    private List<String> playerNames = new ArrayList<String>();
    private String[] playerColors = {"blue", "red", "green"};
    private String myName;

    /**
     * Create a game model.
     *
     * @param pNetworkingClient A NetworkingClient object.
     * @param pGameWindow A GameWindow object.
     */
    public ClientGameLogic(NetworkingClient pNetworkingClient, GameWindow pGameWindow) {
        networkingClient = pNetworkingClient;
        gameWindow = pGameWindow;

        // Ask for player name and then send it to the server.
        myName = gameWindow.askForPlayerName();
        networkingClient.sendPlayerName(myName);
    }

    public ClientGameLogic(NetworkingClient pClient, GameWindow pWindow, String pPlayerName) {
        networkingClient = pClient;
        gameWindow = pWindow;
        myName = pPlayerName;
        networkingClient.sendPlayerName(myName);

    }

    /**
     * Handle that the name which was previously sent to the server was rejected
     * because it was invalid.
     *
     * @param pErrorMessage The error with which the name was rejected
     */
    public void onInvalidPlayerName(String pErrorMessage) {
        gameWindow.showErrorMessage(pErrorMessage);

        // Ask for player name and then send it to the server.
        myName = gameWindow.askForPlayerName();
        networkingClient.sendPlayerName(myName);
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

    /**
     * Get the name of the player.
     *
     * @return the name.s
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Add a player to the game.
     *
     * @param pName The name of the new player.
     */
    public void addPlayer(String pName) {
        playerNames.add(pName);
        gameWindow.addPlayer(pName);
    }

    /**
     * Change whose turn it is.
     *
     * @param pPlayerName
     */
    public void changeTurn(String pPlayerName) {

        // Check if the player is known to the networkingClient.
        if (playerNames.indexOf(pPlayerName) < 0) {
            throw new IllegalArgumentException("Player not found!");
        }

        // Check if it's my turn.
        if (pPlayerName.equals(myName)) {
            this.gameWindow.showControls();
        } else {
            this.gameWindow.hideControls();
        }

        this.gameWindow.setActivePlayer(pPlayerName);
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

        this.gameWindow.setFieldCellColor(pColumn, pRow, color);
    }

    /**
     * Drop a chip in a column. Calculates the position of the top spot and
     * places a chip there.
     *
     * @param pColumn The column in which the chip should be dropped.
     * @return The row position of the chip.
     */
    public void drop(int pColumn) {
        networkingClient.sendDrop(pColumn);
    }

    /**
     * Pass the network networkingClient to this class.
     *
     * @param pClient The network game networkingClient.
     */
    public void setClient(NetworkingClient pClient) {
        networkingClient = pClient;
    }

    /**
     * Pass the gameWindow to this class.
     *
     * @param pClient The gameWindow object.
     */
    public void setWindow(GameWindow pWindow) {
        gameWindow = pWindow;
    }

    /**
     * Called by the networking class when it receives a message from the
     * server, that the game has ended.
     *
     * @param pWinner The name of the winner of the current match.
     */
    public void onGameEnd(String pWinner) {
        if (pWinner.equals(myName)) {
            this.gameWindow.setResultGraphicText("You won!");
        } else {
            this.gameWindow.setResultGraphicText("You lost! Player " + pWinner + " won.");
        }
        this.setGameActive(false);
        this.gameWindow.hideControls();
        this.gameWindow.setActivePlayer(null);
    }

    /**
     * Called by the networking class when the server sends a message to start
     * the game.
     */
    public void resetGame() {
        this.gameWindow.resetPlayingField();
    }

    /**
     * Called by the networking class when the server sends a message to start
     * the game.
     */
    public void onStart() {
        this.resetGame();
        this.setGameActive(true);
    }

    /**
     * Get whether the game is currently in progress or not.
     *
     * @return The games state.
     */
    public boolean getGameActive() {
        return gameActive;
    }

    /**
     * Set whether the game is currently in progress or not.
     *
     * @param pGameActive The games state.
     */
    public void setGameActive(boolean pGameActive) {
        gameActive = pGameActive;
    }

}
