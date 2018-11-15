package ConnectFour.client;

import ConnectFour.server.PlayerStore.Player;

/**
 * Write a description of class GameServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ConnectFourGame {

    private NetworkingClient client;
    private GameWindow window;

    private String[][] playingField = new String[7][7];
    private int numberOfChipsInColumns[] = {0, 0, 0, 0, 0, 0, 0};
    private int numberOfMarks;

    private boolean gameStarted;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private int numberOfPlayers;

    private String gameResult;

    public ConnectFourGame(NetworkingClient pClient, GameWindow pWindow) {
        client = pClient;
        window = pWindow;

        gameStarted = false;
        numberOfPlayers = 0;
        numberOfMarks = 0;
        playerOne = new Player("Player 1");
        playerTwo = new Player("Player 2");

        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 7; row++) {
                playingField[column][row] = " ";
            }
        }

        this.startGame();

    }

    public void startGame() {

        // Ask for player name and then send it to the server.
        String playerName = window.askForPlayerName();
        client.sendPlayerName(playerName);
        this.addPlayer(playerName);

        gameStarted = true;
        currentPlayer = playerOne;
    }

    /**
     * Sets a mark on a specific position on the playing field. This mark is
     * stored in the string array with a specific character for each player.
     *
     * @param pColumn The column where the mark should be set.
     * @param pRow The row where the mark should be set.
     */
    public void setMark(int pColumn, int pRow, int pPlayerNumber) {
        if (playingField[pColumn][pRow].equals(" ")) {
            if (currentPlayer == playerOne) {
                playingField[pColumn][pRow] = "X";
                numberOfMarks++;
                this.switchPlayers();
            } else if (currentPlayer == playerTwo) {
                playingField[pColumn][pRow] = "O";
                numberOfMarks++;
                this.switchPlayers();
            } else {
                System.err.println("Error Resetting: no current player!");
            }
        } else {
            System.err.println("Error setting mark: Cell already marked");
        }

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

    public void switchPlayers() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else if (currentPlayer == playerTwo) {
            currentPlayer = playerOne;
        } else {
            System.err.println("Error while switching palyers: No current player!");
        }
    }

    public void gameEnd(boolean pWon) {
        gameResult = "The game is still running!";
        if (pWon) {
            gameStarted = false;
            gameResult = "The game is over! Winner is: " + currentPlayer.getName();
        } else {
            if (numberOfMarks == 49) {
                gameResult = "The game is over! Draw, there is no winner!";
            }

        }
    }

    public String getPlayerOne() {
        return playerOne.getName();
    }

    public String getPlayerTwo() {
        return playerTwo.getName();
    }

    public void addPlayer(String pName) {
        if (numberOfPlayers == 0) {
            playerOne.setName(pName);
            window.setPlayerOneName(pName);
        } else if (numberOfPlayers == 1) {
            playerTwo.setName(pName);
            window.setPlayerOneName(pName);
        } else {
            System.err.println("Error adding player: Game is full!");
        }

    }

    /**
     * Event listener to be called by the networking client when the server
     * reports that a new enemy has joined the game.
     *
     * @param pName The name of the new enemy.
     */
    public void onNewEnemy(String pName) {
        this.addPlayer(pName);
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
