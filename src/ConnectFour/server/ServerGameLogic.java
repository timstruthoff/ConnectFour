/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectFour.server;

import ConnectFour.server.PlayerStore.Player;
import ConnectFour.server.PlayerStore.PlayerStore;
import java.util.List;

/**
 *
 * @author tmst
 */
public class ServerGameLogic {

    private GameServer gameServer;
    private PlayerStore playerStore = new PlayerStore();
    private int minimumNumberOfPlayers = 2;

    private int currentPlayerNumber = 0;
    private Player currentPlayer;
    private PlayingFieldModel playingFieldModel = new PlayingFieldModel(7, 7);

    private boolean gameActive = false;

    public ServerGameLogic(GameServer pGameServer) {
        gameServer = pGameServer;

    }

    /**
     * Add a new player to the game.
     *
     * @param pName
     * @param pIpAddress
     * @param pPort
     * @return The player.
     */
    public Player addPlayer(String pName, String pIpAddress, int pPort) {
        Player p = playerStore.addPlayer(pName, pIpAddress, pPort);

        return p;
    }

    /**
     * Check whether the player specified by the socket can drop a chip in the
     * specified column number.
     *
     * @param pPlayerIpAddress The player's ip address.
     * @param pPlayerPort The player's port
     * @param pColumn The column in which the chip should be dropped.
     * @return A string either being "SUCCESS" if the player can drop or the
     * error message if the player can't drop.
     */
    public String ableToDrop(String pPlayerIpAddress, int pPlayerPort, int pColumn) {
        Player p = playerStore.getPlayerBySocket(pPlayerIpAddress, pPlayerPort);
        if (!gameActive) {
            return "Game is not active at the moment!";
        }

        if (p == null) {
            return "Player not found! Socket: " + pPlayerIpAddress + ":" + pPlayerPort;
        }

        // Check if it's this player's turn.
        if (playerStore.getNumberOfPlayer(p) != currentPlayerNumber) {
            return "It is not this player's turn!";
        }

        // check if the column numebr is valid.
        if (!playingFieldModel.isValidColumnNumber(pColumn)) {
            return "Invalid column number!";
        }

        // Check if there is a free spot in the selected column.
        if (playingFieldModel.getFreeRowInColumn(pColumn) < 0) {
            return "The selected column " + pColumn + " is full!";
        }

        return "SUCCESS";
    }

    /**
     * Drop a chip in a column
     *
     * @param pPlayerIpAddress The ip address of the player who wants to drop
     * the chip.
     * @param pPlayerPort The port of the player who wants to drop the chip.
     * @param pColumn the column in which the chip should be dropped.
     * @return The row in which the chip was dropped.
     */
    public int drop(String pPlayerIpAddress, int pPlayerPort, int pColumn) {

        // Get the topmost free row.
        int row = this.playingFieldModel.getFreeRowInColumn(pColumn);

        // Get the player who dropped the chip.
        Player p = this.playerStore.getPlayerBySocket(pPlayerIpAddress, pPlayerPort);
        if (p == null) {
            throw new IllegalArgumentException("Player not found!");
        }

        // Set the chip in the playing field.
        this.playingFieldModel.setMark(p, pColumn, row);

        // Check for game outcome
        Player winner = this.playingFieldModel.getWinner();

        if (winner != null) {

            // The game has ended and there is a winner.
            this.gameServer.sendGameEnded(winner);
            this.gameActive = false;
        } else {

            // Turn to the next player.
            this.nextPlayer();
        }

        return row;
    }

    public void onNewPlayer(String pClientIp, int pClientPort, String pName) {

        List<Player> allPlayers = this.getPlayerStore().getAllPlayers();

        System.out.println("Searching for other players:");

        // Sending a message to all other players indicating that a new player has joined.
        for (Player currentPlayer : allPlayers) {
            String currentIp = currentPlayer.getIpAddress();
            int currentPort = currentPlayer.getPort();

            // Notify newly joined player that this player was already in the game.
            this.gameServer.sendNewPlayer(pClientIp, pClientPort, currentPlayer.getName());

            // If player is not the one that has just joined
            if (currentIp.equals(pClientIp) && currentPort == pClientPort) {
                System.out.println("Joined: " + currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());
            } else {
                System.out.println("Other: " + currentPlayer.getName() + " " + currentPlayer.getIpAddress() + ":" + currentPlayer.getPort());

                // Notify player that a new enemy has joined.
                this.gameServer.sendNewPlayer(currentIp, currentPort, pName);

            }
        }

        // Start the game if there are enough players.
        if (this.enoughPlayers() && !this.isGameActive()) {
            this.startGame();
        }
    }

    public PlayerStore getPlayerStore() {
        return playerStore;
    }

    public Player getActivePlayer() {
        return currentPlayer;
    }

    public List<Player> getInactivePlayers() {
        return playerStore.getAllExcept(currentPlayer);
    }

    public void nextPlayer() {

        // Turn to next player.
        currentPlayerNumber++;

        // Check if at end of player list.
        if (currentPlayerNumber >= playerStore.getNumberOfPlayers()) {

            // Go to first player
            currentPlayerNumber = 0;
        }

        // Notify players whose turn it is now.
        currentPlayer = playerStore.getPlayerByNumber(this.currentPlayerNumber);
        this.gameServer.sendTurn(currentPlayer.getName());
    }

    public void resetGame() {
        this.currentPlayerNumber = 0;
        currentPlayer = playerStore.getPlayerByNumber(this.currentPlayerNumber);

        this.playingFieldModel.cleanPlayingField();

        this.gameActive = true;
    }

    public boolean enoughPlayers() {
        return playerStore.getNumberOfPlayers() == minimumNumberOfPlayers;
    }

    public boolean isGameActive() {
        return this.gameActive;
    }

    public void startGame() {
        this.resetGame();
        this.gameServer.sendGameStart();
        this.gameServer.sendTurn(currentPlayer.getName());
    }

}
